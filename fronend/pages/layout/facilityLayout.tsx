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

const FacilityLayout = ({data, title}: any) => {
  return (
    <View style={styles.GridCardContainer}>
      <Text>{title}</Text>
      <FlatList
        data={data}
        scrollEnabled={false}
        renderItem={({item}) => renderCardItem(item)}
        numColumns={2}
        keyExtractor={(item, index) => item + index.toString()}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  GridCardContainer: {
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#FFFFFF',
  },
});

export default FacilityLayout;
