import {KakaoOAuthToken, KakaoProfile} from '@react-native-seoul/kakao-login';
import {atom, atomFamily, selector} from 'recoil';
// import { accessTokenRefresh, reponseTokenData, profile } from "../apis/auth";

export const userAtom = atom<KakaoProfile>({
  key: 'userAtom',
  //   default: null,
});

export const tokenAtom = atom<KakaoOAuthToken>({
  key: 'authState',
  default: {
    accessTokenExpiresAt: new Date(),
    refreshTokenExpiresAt: new Date(),
    scopes: [...''],
    accessToken: '',
    idToken: '',
    refreshToken: '',
  },
});

export const isLoggedIn = atom<Boolean>({
  key: 'isLoggedIn',
  default: false,
});

export default userAtom;
