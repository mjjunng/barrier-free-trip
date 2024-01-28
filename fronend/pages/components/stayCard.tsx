import React from 'react';
import {Image, StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import Svg, {Path} from 'react-native-svg';

const cardStyles = StyleSheet.create({
  card: {
    width: 150,
    height: 203,
    margin: 12,
  },
  image: {
    overflow: 'hidden',
    height: 100,
    width: 150,
    borderRadius: 8,
  },
  grade: {
    width: 17,
    height: 17,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '500',
    fontSize: 12,
    lineHeight: 17,
    letterSpacing: -0.165,
    color: '#2A2F37',
  },
  gradeCount: {
    width: 28,
    height: 17,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '400',
    fontSize: 12,
    lineHeight: 17,
    letterSpacing: -0.165,
    color: '#9F9F9F',
  },
  title: {
    width: 118,
    height: 20,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '500',
    fontSize: 14,
    lineHeight: 20,
    letterSpacing: -0.165,
    color: '#000000',
  },
  description: {
    width: 62,
    height: 22,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '700',
    fontSize: 15,
    lineHeight: 22,
    letterSpacing: -0.165,
    color: '#000000',
  },
  location: {
    width: 77,
    height: 17,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '400',
    fontSize: 12,
    lineHeight: 17,
    letterSpacing: -0.165,
    color: '#9F9F9F',
  },
});

interface StayCardProps {
  imgSrc: string;
  title: string;
  description: string;
  grade: string;
  gradeCount: number;
  location: string;
}

export const StayCard = ({
  imgSrc,
  title,
  description,
  grade,
  gradeCount,
  location,
}: StayCardProps) => {
  return (
    <TouchableOpacity style={cardStyles.card}>
      <Image source={{uri: imgSrc}} style={cardStyles.image} />
      <View style={{flexDirection: 'row'}}>
        <Svg width={16} height={16} viewBox="0 0 16 16" fill="none">
          <Path
            d="M9.62001 6.66667L8.64001 3.44C8.44668 2.80667 7.55335 2.80667 7.36668 3.44L6.38001 6.66667H3.41335C2.76668 6.66667 2.50001 7.5 3.02668 7.87333L5.45335 9.60667L4.50001 12.68C4.30668 13.3 5.02668 13.8 5.54001 13.4067L8.00001 11.54L10.46 13.4133C10.9733 13.8067 11.6933 13.3067 11.5 12.6867L10.5467 9.61333L12.9733 7.88C13.5 7.5 13.2333 6.67333 12.5867 6.67333H9.62001V6.66667Z"
            fill="#FFDA2D"
          />
        </Svg>
        <Text style={cardStyles.grade}>{grade}</Text>
        <Text style={cardStyles.gradeCount}>({gradeCount})</Text>
      </View>
      <Text style={cardStyles.title}>{title}</Text>
      <Text style={cardStyles.location}>{location}</Text>
      <Text style={cardStyles.description}>{description}</Text>
    </TouchableOpacity>
  );
};

StayCard.title = 'Card';
