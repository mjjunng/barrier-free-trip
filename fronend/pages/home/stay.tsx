import React, {useEffect} from 'react';
import {Text} from 'react-native-paper';
import {getSidoCode} from '../api/religionSelection';
import {View} from 'react-native';
import ReligionSelects from '../components/religionSelects';
import {globalStyles} from '../style/global';

export const StayPage = () => {
  return (
    <View style={globalStyles.container}>
      <Text style={[globalStyles.subTitleText, {marginBottom: 16}]}>
        지역선택
      </Text>

      <ReligionSelects />
    </View>
  );
};
