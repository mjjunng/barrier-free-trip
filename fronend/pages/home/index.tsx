import React from 'react';
import {GridCard} from '../components/gridCard';
import {CareCard} from '../components/careCard';
import {useTheme} from 'react-native-paper';
import {
  FlatList,
  Image,
  ScrollView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';
import {createStackNavigator} from '@react-navigation/stack';
import {
  Handshake,
  Heart,
  Lightning,
  Restaurant,
  Stay,
  Tourlist,
} from '../icons/homaPageStackNavigatorIcons';
import {StayPage} from './stay';
import Temp from '../Temp';
import FacilityLayout from '../layout/facilityLayout';
import ImageSlider from '../layout/thickListLayout';
import {ListCard} from '../components/listCard';
import { DetailPage } from '../components/detailPage';

const Home = ({navigation}: any) => {
  const theme = useTheme();

  return (
    <ScrollView
      contentContainerStyle={[
        homeStyles.stayCardContainer,
        {backgroundColor: theme.colors.secondary},
      ]}>
      {/* <ImageSlider /> */}
      <View style={homeStyles.logoSection} />
      <View style={homeStyles.iconsContainer}>
        <TouchableOpacity
          style={homeStyles.icons}
          onPress={() => navigation.navigate('숙박')}>
          <Image source={Stay} />
          <Text style={homeStyles.iconLabel}>숙박</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={homeStyles.icons}
          onPress={() => navigation.navigate('관광지')}>
          <Image source={Tourlist} />
          <Text style={homeStyles.iconLabel}>관광지</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={homeStyles.icons}
          onPress={() => navigation.navigate('음식점')}>
          <Image source={Restaurant} />
          <Text style={homeStyles.iconLabel}>음식점</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={homeStyles.icons}
          onPress={() => navigation.navigate('돌봄여행')}>
          <Image source={Heart} />
          <Text style={homeStyles.iconLabel}>돌봄여행</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={homeStyles.icons}
          onPress={() => navigation.navigate('충전기')}>
          <Image source={Lightning} />
          <Text style={homeStyles.iconLabel}>충전기</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={homeStyles.icons}
          onPress={() => navigation.navigate('렌탈')}>
          <Image source={Handshake} />
          <Text style={homeStyles.iconLabel}>렌탈</Text>
        </TouchableOpacity>
      </View>
      <FacilityLayout data={DATA} title={'내 주변 숙박시설'} />
      <ListCard data={DataForList} title={'내 주변 관광지'} />
    </ScrollView>
  );
};
const Stack = createStackNavigator();

export const HomeNavigator = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen
        name="Home"
        component={Home}
        options={{headerShown: false}}
      />
      <Stack.Screen name="숙박" component={StayPage} />
      <Stack.Screen name="관광지" component={Home} />
      <Stack.Screen name="음식점" component={Temp} />
      <Stack.Screen name="돌봄여행" component={Home} />
      <Stack.Screen name="충전기" component={Home} />
      <Stack.Screen name="렌탈" component={Home} />
      <Stack.Screen name="DetailNavigator" component={DetailPage} />
    </Stack.Navigator>
  );
};

const homeStyles = StyleSheet.create({
  logoSection: {
    width: 360,
    height: 52,
  },
  stayCardContainer: {
    // height: 454,
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#FFFFFF',
  },
  iconLabel: {
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 12,
  },
  iconsContainer: {
    // width: '100%',

    height: 180,
    // justifyContent: 'center',
    alignItems: 'center',
    justifyContent: 'center',
    // position: 'relative',
    // display: 'flex',
    flexDirection: 'row',
    flexWrap: 'wrap',
    backgroundColor: '#FFFFFF',
    padding: 20,
    marginBottom: 12,
  },
  icons: {
    // top: '50%',
    // paddingLeft: 30,
    // paddingRight: 30,
    marginTop: 10,
    // marginLeft: 'auto',
    marginLeft: 23,
    marginRight: 23,
    // marginLeft: 'auto',
    // marginRight: 'auto',
    height: 70,
    width: 70,
    alignItems: 'center',
  },
});

// contentId: string;
// firstimg: string;
// title: string;
// address: string;
// rating: string;
// gradeCount: number;
// location: string;
const DATA = [
  {
    contentId: '126486',
    firstimg: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    address: 'Lorem ipsum dolor sit amet',
    rating: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
  {
    contentId: '126486',
    firstimg: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    address: 'Lorem ipsum dolor sit amet',
    rating: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
  {
    contentId: '126486',
    firstimg: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    address: 'Lorem ipsum dolor sit amet',
    rating: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
  {
    contentId: '126486',
    firstimg: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    address: 'Lorem ipsum dolor sit amet',
    rating: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
];

const DataForList = [
  {
    contentId: '126486',
    contentTypeId: '126486',
    title: '도산공원',
    address: '서울특별시 강남구 도산대로45길 20',
    rating: 4,
    firstimg:
      'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
  },
  {
    contentId: '126504',
    contentTypeId: '126504',
    title: '봉은사(서울)',
    address: '서울특별시 강남구 봉은사로 531',
    rating: 3,
    firstimg:
      'http://tong.visitkorea.or.kr/cms/resource/37/2652137_image2_1.jpg',
  },
  {
    contentId: '126525',
    contentTypeId: '126525',
    title: '서울 선릉(성종과 정현왕후)과 정릉(중종) [유네스코 세계문화유산]',
    address: '서울특별시 강남구 선릉로100길 1',
    rating: 2,
    firstimg: '',
  },
  {
    contentId: '1602451',
    contentTypeId: '1602451',
    title: '대모산공원',
    address: '서울특별시 강남구 광평로10길 30-71',
    rating: 1,
    firstimg:
      'http://tong.visitkorea.or.kr/cms/resource/51/1567751_image2_1.jpg',
  },
  {
    contentId: '2456536',
    contentTypeId: '2456536',
    title: '강남 마이스 관광특구',
    address: '서울특별시 강남구 영동대로 513',
    rating: 5,
    firstimg: '',
  },
  {
    contentId: '2614315',
    contentTypeId: '2614315',
    title: 'AHC스파',
    address: '서울특별시 강남구 도산대로 237',
    rating: 6,
    firstimg:
      'http://tong.visitkorea.or.kr/cms/resource/05/2614305_image2_1.bmp',
  },
];
