// 충전기, 돌봄여행

import React from 'react';
import {Image, StyleSheet, View, Text} from 'react-native';
import {List} from 'react-native-paper';

interface ListCardProps {
  title: string;
  data: AllData[];
}

interface AllData {
  contentId: string;
  contentTypeId: string;
  title: string;
  address: string;
  rating: number;
  firstimg: string;
}

export const ListCard = ({data, title}: ListCardProps) => {
  // const title = 'List item 1';
  const description =
    'Describes item 1. Example of a very very long description.';
  const tel = '041-503-203';
  return (
    <View style={styles.container}>
      <Text style={styles.text}>{title}</Text>
      <List.Section>
        {data.map((value, index) => (
          <List.Item
            style={styles.listItem}
            left={() => (
              <View style={styles.imgCover}>
                <Image
                  source={require('../../public/charger-sample.png')}
                  style={styles.image}
                />
              </View>
            )}
            title={<Text style={styles.title}>{value.title}</Text>}
            description={
              <View>
                <Text style={styles.description}>{value.address}</Text>
                <Text style={styles.tel}>{tel}</Text>
              </View>
            }
          />
        ))}
      </List.Section>
    </View>
  );
};

const styles = StyleSheet.create({
  text: {
    width: '100%',
    height: 28,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '500',
    fontSize: 16,
    lineHeight: 28,
    letterSpacing: -0.4,
    color: '#000000',
    marginLeft: 33,
    marginTop: 10,
  },
  container: {
    marginTop: 10,
    width: '100%',
    backgroundColor: '#FFFFFF',
  },
  image: {
    height: 40,
    width: 40,
    top: '20%',
    alignItems: 'center',
  },
  imgCover: {
    height: 60,
    width: 60,
    alignItems: 'center',
    borderRadius: 14,

    backgroundColor: '#FFF5F5',
  },
  listItem: {
    // width: 309,
    // alignItems: 'center',
    height: 68,
    // paddingTop: 16,
    // paddingBottom: 16,
    // paddingLeft: 25,
    // paddingRight: 25,

    // marginTop: 16,
    marginBottom: 16,
    marginLeft: 25,
    // marginRight: 25,
  },
  title: {
    fontSize: 18,
  },
  description: {
    fontSize: 10,
  },
  tel: {
    width: 61,
    height: 14,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '400',
    fontSize: 10,
    lineHeight: 14,
    letterSpacing: -0.165,
    color: '#828F9C',
    flex: 0,
    order: 0,
    flexGrow: 0,
  },
});
