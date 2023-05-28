import React from 'react';
import {StayCard} from '../components/stayCard';
import {CareCard} from '../components/careCard';
import {FlatList, ScrollView, Text, View} from 'react-native';

const DATA = [
  {
    imgSrc: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: 'Ttitle1',
    description: 'Lorem ipsum dolor sit amet',
    grade: '4.3',
    gradeCount: 423,
    location: '서울역 도보 7분',
  },
  {
    imgSrc: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
    title: 'Ttitle2',
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
      <FlatList
        data={DATA}
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
