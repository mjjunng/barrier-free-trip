import React from 'react';
import {useRecoilValue} from 'recoil';
import {OAuthLogin} from './auth';
import {NavigationContainer} from '@react-navigation/native';
import {isLoggedInAtom} from '../store/user';
import BottomTabs from './bottomTabs';
import {createStackNavigator} from '@react-navigation/stack';
import Temp from './Temp';

const Stack = createStackNavigator();

export const FirstPageController = () => {
  const isLoggedIn = useRecoilValue(isLoggedInAtom);
  console.log(isLoggedIn);

  return (
    <>
      {isLoggedIn ? (
        <>
          <BottomTabs />
        </>
      ) : (
        <OAuthLogin />
      )}
    </>
  );
};
