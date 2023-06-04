import React, {useEffect} from 'react';
import {Text} from 'react-native-paper';
import {getSidoCode} from '../api/religionSelection';
import {View} from 'react-native';

export const StayPage = () => {
  useEffect(() => {
    const fetchData = async () => {
      const profile = await getSidoCode();
      console.log(profile);
    };

    fetchData();
  }, []);

  return <Text>Stay Page</Text>;
};
