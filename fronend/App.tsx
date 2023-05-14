import * as React from 'react';
import {useColorScheme, StatusBar} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import OAuthLogin from './pages/login';
import {Colors} from 'react-native/Libraries/NewAppScreen';

const Stack = createNativeStackNavigator();

function App() {
  const isDarkMode = useColorScheme() === 'dark';
  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <NavigationContainer>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={OAuthLogin} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default App;
