import {StyleSheet} from 'react-native';
import {View, Text, Image, TouchableOpacity} from 'react-native';
import KakaoLogin from '../../public/KakaoLogin.png';
import NaverLogin from '../../public/NaverLogin.png';
import React from 'react';
import {
  login,
  logout,
  getProfile as getKakaoProfile,
  unlink,
} from '@react-native-seoul/kakao-login';
import {useRecoilValue, useSetRecoilState} from 'recoil';
import {tokenAtom} from '../../store/user';

const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    top: '50%',
    margin: 50,
    // padding: 30,
    // width: 296,
    // height: 150,
    // transform: 'translate(-50%, -50%)',
  },
  sns: {
    fontFamily: 'NotoSans-Display',
    fontSize: 12,
    fontWeight: 'normal',
    fontStyle: 'normal',
    textAlign: 'left',
    color: '#6a6d76',
    paddingBottom: 20,
  },
});

const OAuthLogin = () => {
  const setToken = useSetRecoilState(tokenAtom);
  const tokenTemp = useRecoilValue(tokenAtom);

  return (
    <View style={styles.container}>
      <Text style={styles.sns}>SNS 계정으로 로그인</Text>
      <Text>{JSON.stringify(tokenTemp)}</Text>
      <TouchableOpacity
        onPress={async () => {
          const token = await login();
          setToken(token);
        }}>
        <Image source={KakaoLogin} />
      </TouchableOpacity>

      <TouchableOpacity style={{marginTop: 16}}>
        <Image source={NaverLogin} />
      </TouchableOpacity>

      {/* Rest of the code */}
    </View>
  );
};

export default OAuthLogin;
