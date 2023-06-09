import React, {useState, useEffect, useRef} from 'react';
import {
  View,
  Text,
  SafeAreaView,
  StatusBar,
  Dimensions,
  StyleSheet,
  ScrollView,
} from 'react-native';
const {width} = Dimensions.get('window');
import SelectDropdown from 'react-native-select-dropdown';
import {getSidoCode, getSigoonguCode} from '../api/religionSelection';
import {FontAwesomeIcon} from '@fortawesome/react-native-fontawesome';
import {faChevronDown, faChevronUp} from '@fortawesome/free-solid-svg-icons';

interface Sido {
  title: string;
  code: string;
}
interface Sigoongu {
  title: string;
  code: string;
}

const ReligionSelects: React.FC = () => {
  const [sidos, setSidos] = useState<Sido[]>([]);
  const [sigoongus, setSigoongus] = useState<Sigoongu[]>([]);

  const citiesDropdownRef = useRef<SelectDropdown>(null);

  useEffect(() => {
    const fetchData = async () => {
      const sido = await getSidoCode().then(
        res => res.response.result.featureCollection.features,
      );

      const ctp_kor_nm_list = sido.map((item: any) => ({
        title: item.properties.ctp_kor_nm,
        code: item.properties.ctprvn_cd,
      }));

      setSidos(ctp_kor_nm_list);
    };

    fetchData();
  }, []);

  return (
    // <SafeAreaView style={styles.saveAreaViewContainer}>
    <SafeAreaView>
      <StatusBar backgroundColor="#FFF" barStyle="dark-content" />
      {/* <View style={styles.viewContainer}> */}
      <View>
        <ScrollView
          showsVerticalScrollIndicator={false}
          alwaysBounceVertical={false}
          contentContainerStyle={styles.scrollViewContainer}>
          <View style={styles.dropdownsRow}>
            <SelectDropdown
              data={sidos.map(sido => sido.title)}
              onSelect={async (selectedItem: string, index: number) => {
                const selectedSido = sidos[index];
                citiesDropdownRef.current?.reset();
                const sigoongu = await getSigoonguCode(selectedSido.code).then(
                  res => res.response.result.featureCollection.features,
                );
                const sig_kor_nm = sigoongu.map((item: any) => ({
                  title: item.properties.sig_kor_nm,
                  code: item.properties.sig_cd,
                }));

                setSigoongus([]);
                setSigoongus(sig_kor_nm);
              }}
              defaultButtonText={'시도 선택'}
              buttonStyle={styles.dropdown1BtnStyle}
              buttonTextStyle={styles.dropdown1BtnTxtStyle}
              renderDropdownIcon={(isOpened: boolean) => {
                return (
                  <FontAwesomeIcon
                    icon={isOpened ? faChevronUp : faChevronDown}
                    color="#444"
                    size={18}
                  />
                );
              }}
              dropdownIconPosition={'right'}
              dropdownStyle={styles.dropdown1DropdownStyle}
              rowStyle={styles.dropdown1RowStyle}
              rowTextStyle={styles.dropdown1RowTxtStyle}
            />
            <View style={styles.divider} />
            <SelectDropdown
              ref={citiesDropdownRef}
              data={sigoongus.map(sigoongu => sigoongu.title)}
              onSelect={(selectedItem: Sido, index: number) => {
                console.log(selectedItem, index);
              }}
              defaultButtonText={'시군구 선택'}
              buttonStyle={styles.dropdown2BtnStyle}
              buttonTextStyle={styles.dropdown2BtnTxtStyle}
              renderDropdownIcon={(isOpened: boolean) => {
                return (
                  <FontAwesomeIcon
                    icon={isOpened ? faChevronUp : faChevronDown}
                    color="#444"
                    size={18}
                  />
                );
              }}
              dropdownIconPosition={'right'}
              dropdownStyle={styles.dropdown2DropdownStyle}
              rowStyle={styles.dropdown2RowStyle}
              rowTextStyle={styles.dropdown2RowTxtStyle}
            />
          </View>
        </ScrollView>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  shadow: {
    shadowColor: '#000',
    shadowOffset: {width: 0, height: 6},
    shadowOpacity: 0.1,
    shadowRadius: 10,
    elevation: 10,
  },
  header: {
    flexDirection: 'row',
    width,
    height: 50,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#F6F6F6',
  },
  saveAreaViewContainer: {flex: 1, backgroundColor: '#FFF'},
  viewContainer: {flex: 1, width, backgroundColor: '#FFF'},
  scrollViewContainer: {
    flexGrow: 1,
    justifyContent: 'space-between',
    alignItems: 'center',
    // paddingVertical: '10%',
  },
  dropdownsRow: {flexDirection: 'row', width: '100%'},

  dropdown1BtnStyle: {
    flex: 1,
    height: 50,
    backgroundColor: '#FFF',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#444',
  },
  dropdown1BtnTxtStyle: {color: '#444', textAlign: 'left'},
  dropdown1DropdownStyle: {backgroundColor: '#EFEFEF'},
  dropdown1RowStyle: {backgroundColor: '#EFEFEF', borderBottomColor: '#C5C5C5'},
  dropdown1RowTxtStyle: {color: '#444', textAlign: 'left'},
  divider: {width: 12},
  dropdown2BtnStyle: {
    flex: 1,
    height: 50,
    backgroundColor: '#FFF',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#444',
  },
  dropdown2BtnTxtStyle: {color: '#444', textAlign: 'left'},
  dropdown2DropdownStyle: {backgroundColor: '#EFEFEF'},
  dropdown2RowStyle: {backgroundColor: '#EFEFEF', borderBottomColor: '#C5C5C5'},
  dropdown2RowTxtStyle: {color: '#444', textAlign: 'left'},
});

export default ReligionSelects;
