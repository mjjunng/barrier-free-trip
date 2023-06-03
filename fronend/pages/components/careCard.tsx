import React from 'react';
import {Image, StyleSheet, View} from 'react-native';
import {List, Text} from 'react-native-paper';

export const CareCard = () => {
  const title = 'List item 1';
  const description =
    'Describes item 1. Example of a very very long description.';

  return (
    <List.Section>
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
        title={<Text style={styles.title}>{title}</Text>}
        description={<Text style={styles.description}>{description}</Text>}
      />
    </List.Section>
  );
};

const styles = StyleSheet.create({
  image: {
    height: 40,
    width: 40,
    top: '10%',
  },
  imgCover: {
    height: 60,
    width: 60,
    alignItems: 'center',

    // backgroundColor:
  },
  listItem: {
    width: 309,
    height: 68,
    // paddingTop: 16,
    // paddingBottom: 16,
    // paddingLeft: 25,
    // paddingRight: 25,

    marginTop: 16,
    marginBottom: 16,
    marginLeft: 25,
    marginRight: 25,
  },
  title: {
    fontSize: 18,
  },
  description: {
    fontSize: 10,
  },
});
