import React from 'react';
import {createMaterialBottomTabNavigator} from '@react-navigation/material-bottom-tabs';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import OAuthLogin from './login';

const Tab = createMaterialBottomTabNavigator();

export default function BottomTabs() {
  return (
    <Tab.Navigator>
      <Tab.Screen name="홈" component={OAuthLogin} />
      <Tab.Screen name="검색" component={OAuthLogin} />
      <Tab.Screen name="지도" component={OAuthLogin} />
      <Tab.Screen name="찜" component={OAuthLogin} />
      <Tab.Screen name="마이" component={OAuthLogin} />
    </Tab.Navigator>
  );
}
