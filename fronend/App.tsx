import * as React from 'react';
import {useColorScheme, StatusBar} from 'react-native';
import {Colors} from 'react-native/Libraries/NewAppScreen';
import {RecoilRoot} from 'recoil';
import {FirstPageController} from './pages/firstPageController';

function App() {
  const isDarkMode = useColorScheme() === 'dark';
  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <RecoilRoot>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <FirstPageController />
    </RecoilRoot>
  );
}

export default App;
