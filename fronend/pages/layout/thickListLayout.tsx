import React from 'react';
import {Image, StyleSheet, View} from 'react-native';
import Swiper from 'react-native-swiper';

// 돌봄 여행 List
interface ThickListLayoutPropps {
  title: string;
  addr: string;
  tel: string;
  like: 0;
}

const ImageSlider = () => {
  const images = [
    'https://images.unsplash.com/photo-1560807707-8cc77767d783',
    'https://images.unsplash.com/photo-1560807707-8cc77767d783',
    'https://images.unsplash.com/photo-1560807707-8cc77767d783',
  ];

  return (
    <Swiper>
      {images.map((image, index) => (
        <View key={index}>
          <Image
            source={{uri: image}}
            style={{width: '100%', height: '100%'}}
          />
        </View>
      ))}
    </Swiper>
  );
};

export default ImageSlider;

const styles = StyleSheet.create({
  container: {
    width: 300,
    height: 245,
  },
  image: {
    width: 300,
    height: 160,
    borderRadius: 20,
  },
  title: {
    width: 165,
    height: 28,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '400',
    fontSize: 15,
    lineHeight: 28,
    letterSpacing: -0.4,
    color: '#2A2F37',
  },
  location: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 0,
    gap: 2,
    width: 265,
    height: 20,
  },
  number: {
    width: 74,
    height: 17,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '400',
    fontSize: 12,
    lineHeight: 17,
    letterSpacing: -0.165,
    color: '#828F9C',
  },
  goToMap: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'flex-start',
    padding: 5,
    paddingHorizontal: 8,
    gap: 10,
    position: 'absolute',
    width: 53,
    height: 24,
    left: 46,
    top: 70,
    backgroundColor: '#FFA6A6',
    borderRadius: 32,
  },
});
