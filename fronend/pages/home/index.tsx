import React from 'react';
import {StayCard} from '../components/stayCard';
import {CareCard} from '../components/careCard';
import {
  Button,
  FlatList,
  Image,
  SafeAreaView,
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

const Home = ({navigation}: any) => {
  return (
    <ScrollView contentContainerStyle={homeStyles.stayCardContainer}>
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
      <FlatList
        contentContainerStyle={homeStyles.stayCardContainer}
        data={DATA}
        scrollEnabled={false}
        renderItem={({item}) => (
          <StayCard
            imgSrc={item.imgSrc}
            title={item.title}
            description={item.description}
            grade={item.grade}
            gradeCount={item.gradeCount}
            location={item.location}
          />
        )}
        numColumns={2}
        keyExtractor={(item, index) => index.toString()}
      />
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
  stayCardContainer: {
    // width: 325,
    // height: 454,
    alignItems: 'center',
    justifyContent: 'center',
  },
  iconLabel: {
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 10,
  },
  iconsContainer: {
    width: 250,
    height: 136,
    // justifyContent: 'center',
    alignItems: 'center',
    justifyContent: 'center',
    // position: 'relative',
    // display: 'flex',
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  icons: {
    // padding: 100,
    marginTop: 10,
    marginLeft: 'auto',
    marginRight: 'auto',
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
