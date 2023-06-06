import React, {useEffect} from 'react';
import {Text} from 'react-native-paper';
import {getSidoCode} from '../api/religionSelection';
import {View} from 'react-native';
import ReligionSelects from '../components/religionSelects';

export const StayPage = () => {
  useEffect(() => {
    const fetchData = async () => {
      const sido = await getSidoCode().then(
        res => res.response.result.featureCollection.features,
      );
      console.log(sido);
    };

    fetchData();
  }, []);

  return <ReligionSelects />;
};
