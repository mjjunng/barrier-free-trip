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

const DATA = [
  {
    imgSrc: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    description: 'Lorem ipsum dolor sit amet',
    grade: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
  {
    imgSrc: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    description: 'Lorem ipsum dolor sit amet',
    grade: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
  {
    imgSrc: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    description: 'Lorem ipsum dolor sit amet',
    grade: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
  {
    imgSrc: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: '신라호텔',
    description: 'Lorem ipsum dolor sit amet',
    grade: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
];
