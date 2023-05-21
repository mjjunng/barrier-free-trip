import React from 'react';
import {useRecoilValue} from 'recoil';
import {OAuthLogin} from './auth';
import {NavigationContainer} from '@react-navigation/native';
import {isLoggedInAtom} from '../store/user';
import BottomTabs from './BottomTabs';

export const FirstPageController = () => {
  const isLoggedIn = useRecoilValue(isLoggedInAtom);
  console.log(isLoggedIn);

  return (
    <>
      {isLoggedIn ? (
        <NavigationContainer>
          <BottomTabs />
        </NavigationContainer>
      ) : (
        <OAuthLogin />
      )}
    </>
  );
};
