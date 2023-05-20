import React from 'react';
import {logout} from '@react-native-seoul/kakao-login';
import {Text, TouchableOpacity} from 'react-native';
import {useSetRecoilState} from 'recoil';
import {isLoggedIn} from '../../store/user';

export const OAuthLogout = () => {
  const setIsLoggedIn = useSetRecoilState(isLoggedIn);

  return (
    <TouchableOpacity
      style={{marginTop: 16}}
      onPress={async () => {
        const logoutResult = await logout();
        console.log(
          '🚀 ~ file: index.tsx:64 ~ onPress={ ~ logoutResult:',
          logoutResult,
        );
        setIsLoggedIn(false);
      }}>
      <Text>logout</Text>
    </TouchableOpacity>
  );
};
