import {StyleSheet} from 'react-native';
import React from 'react';
import {View, Text, Image, TouchableOpacity} from 'react-native';
import KakaoLogin from '../../public/KakaoLogin.png';
import NaverLogin from '../../public/NaverLogin.png';

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
  return (
    <View style={styles.container}>
      <Text style={styles.sns}>SNS 계정으로 로그인</Text>

      <TouchableOpacity>
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
