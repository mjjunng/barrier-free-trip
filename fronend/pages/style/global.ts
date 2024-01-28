import {StyleSheet} from 'react-native';
import {MD3LightTheme as DefaultTheme} from 'react-native-paper';

export const globalStyles = StyleSheet.create({
  View: {
    padding: 0,
  },
  container: {
    margin: 24,
    flex: 1,
  },
  titleText: {
    fontFamily: 'nunito-bold',
    fontSize: 18,
    color: '#333',
  },
  paragraph: {
    marginVerical: 8,
    lineHeight: 20,
  },
  subTitleText: {
    width: 63,
    height: 23,
    fontFamily: 'Noto Sans KR',
    fontStyle: 'normal',
    fontWeight: '500',
    fontSize: 16,
    color: '#000000',
  },
});

export const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: '#FFA6A6',
    secondary: '#FFF5F5',
  },
};
