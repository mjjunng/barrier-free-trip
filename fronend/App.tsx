import * as React from 'react';
import {useColorScheme, StatusBar} from 'react-native';
import {Colors} from 'react-native/Libraries/NewAppScreen';
import {RecoilRoot} from 'recoil';
import {FirstPageController} from './pages/firstPageController';
import {PaperProvider} from 'react-native-paper';
import {theme} from './pages/style/global';
import {NavigationContainer} from '@react-navigation/native';

function App() {
  const isDarkMode = useColorScheme() === 'dark';
  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <NavigationContainer>
      <PaperProvider theme={theme}>
        <RecoilRoot>
          <StatusBar
            barStyle={isDarkMode ? 'light-content' : 'dark-content'}
            backgroundColor={backgroundStyle.backgroundColor}
          />
          <FirstPageController />
        </RecoilRoot>
      </PaperProvider>
    </NavigationContainer>
  );
}

export default App;
