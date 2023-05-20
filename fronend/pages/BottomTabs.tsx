import React from 'react';
import {createMaterialBottomTabNavigator} from '@react-navigation/material-bottom-tabs';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import Temp from './temp';
import {OAuthLogout} from './auth/logout';

const Tab = createMaterialBottomTabNavigator();

export default function BottomTabs() {
  return (
    <Tab.Navigator>
      <Tab.Screen name="홈" component={Temp} />
      <Tab.Screen name="검색" component={OAuthLogout} />
      <Tab.Screen name="지도" component={Temp} />
      <Tab.Screen name="찜" component={Temp} />
      <Tab.Screen name="마이" component={Temp} />
    </Tab.Navigator>
  );
}
