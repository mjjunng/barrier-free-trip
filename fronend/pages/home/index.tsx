import React from 'react';
import {StayCard} from '../components/stayCard';
import {CareCard} from '../components/careCard';
import {
  FlatList,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';

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

export const Home = () => {
  return (
    <ScrollView>
      <View style={homeStyles.iconsContainer}></View>
      <FlatList
        contentContainerStyle={{
          alignItems: 'center',
          justifyContent: 'center',
        }}
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
      <CareCard />
    </ScrollView>
  );
};

const homeStyles = StyleSheet.create({
  stayCardContainer: {
    width: 325,
    height: 454,
  },
  iconsContainer: {
    width: 250,
    height: 136,
    backgroundColor: 'black',
    alignItems: 'center',
    alignContent: 'center',
  },
});
