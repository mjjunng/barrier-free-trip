import React from 'react';
import {useRecoilValue} from 'recoil';
import {OAuthLogin} from './auth';
import BottomTabs from './BottomTabs';
import isLoggedIn from '../store/user';
import {NavigationContainer} from '@react-navigation/native';
import {View} from 'react-native';

export const FirstPageController = () => {
  const isLoggedInRecoil = useRecoilValue(isLoggedIn);
  console.log('isLoggedIn', isLoggedIn);

  return (
    <View>
      {isLoggedInRecoil ? (
        <OAuthLogin />
      ) : (
        <NavigationContainer>
          <BottomTabs />
        </NavigationContainer>
      )}
    </View>
  );
};
