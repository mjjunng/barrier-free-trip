import React from 'react';
import {StyleSheet, Text, TextComponent} from 'react-native';
import {Card} from 'react-native-paper';
type Mode = 'elevated' | 'outlined' | 'contained';

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  content: {
    padding: 4,
  },
  card: {
    margin: 4,
  },
  chip: {
    margin: 4,
  },
  preference: {
    alignItems: 'center',
    flexDirection: 'row',
    paddingVertical: 12,
    paddingHorizontal: 8,
  },
  button: {
    borderRadius: 12,
  },
});

export const StayCard = () => {
  const [selectedMode, setSelectedMode] = React.useState('elevated' as Mode);

  return (
    <Card style={styles.card} mode={selectedMode}>
      <Card.Cover
        source={{
          uri: 'http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg',
        }}
      />
      <Card.Title title="Abandoned Ship" />
      <Card.Content>
        <Text>
          The Abandoned Ship is a wrecked ship located on Route 108 in Hoenn,
          originally being a ship named the S.S. Cactus. The second part of the
          ship can only be accessed by using Dive and contains the Scanner.
        </Text>
      </Card.Content>
    </Card>
  );
};

StayCard.title = 'Card';
