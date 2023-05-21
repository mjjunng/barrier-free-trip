import React from 'react';
import {createMaterialBottomTabNavigator} from '@react-navigation/material-bottom-tabs';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import {OAuthLogout} from './auth/logout';
import Temp from './Temp';
import {View} from 'react-native';

const Tab = createMaterialBottomTabNavigator();

export default function BottomTabs() {
  return (
    <View>
      <Tab.Navigator>
        <Tab.Screen name="홈" component={Temp} />
        <Tab.Screen name="검색" component={Temp} />
        <Tab.Screen name="지도" component={Temp} />
        <Tab.Screen name="찜" component={Temp} />
        <Tab.Screen name="마이" component={Temp} />
      </Tab.Navigator>
    </View>
  );
}
