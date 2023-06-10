import {FlatList, StyleSheet, Text, View} from 'react-native';
import {GridCard} from '../components/gridCard';
import React from 'react';
interface GridCardProps {
  imgSrc: string;
  title: string;
  description: string;
  grade: string;
  gradeCount: number;
  location: string;
}

const renderCardItem = (item: GridCardProps) => {
  return (
    <GridCard
      imgSrc={item.imgSrc}
      title={item.title}
      description={item.description}
      grade={item.grade}
      gradeCount={item.gradeCount}
      location={item.location}
    />
  );
};

const styles = StyleSheet.create({
  Text: {
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
  GridCardContainer: {
    width: '100%',
    backgroundColor: '#FFFFFF',
  },
  FlatListContainer: {
    alignItems: 'center',
  },
});

const FacilityLayout = ({data, title}: any) => {
  return (
    <View style={styles.GridCardContainer}>
      <Text style={styles.Text}>{title}</Text>
      <View style={styles.FlatListContainer}>
        <FlatList
          data={data}
          scrollEnabled={false}
          renderItem={({item}) => renderCardItem(item)}
          numColumns={2}
          keyExtractor={(item, index) => item + index.toString()}
        />
      </View>
    </View>
  );
};

export default FacilityLayout;
