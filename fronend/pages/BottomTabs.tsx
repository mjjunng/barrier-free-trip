import React from 'react';
import {createMaterialBottomTabNavigator} from '@react-navigation/material-bottom-tabs';
import Temp from './Temp';
import {Image, View} from 'react-native';
import {
  Heart,
  HeartOutline,
  Home,
  HomeOutline,
  Location,
  LocationOutline,
  Search,
  SearchOutline,
  Smaile,
  SmaileOutline,
} from './icons/bottomTabNavigatorIcons';
import {Home as HomePage} from './home';

const Tab = createMaterialBottomTabNavigator();

const CustomTabNavigator = () => (
  <Tab.Navigator
    // activeColor="#f0edf6"
    // inactiveColor="#3e2465"
    // barStyle={{backgroundColor: '#694fad'}}
    // shifting={false}
    // tabBarActiveBackgroundColor=
    // sceneAnimationEnabled={false}
    screenOptions={({route}) => ({
      tabBarIcon: ({focused, color}) => {
        var iconSource;
        console.log(route.name);
        if (route.name === '홈') {
          iconSource = focused ? Home : HomeOutline;
        } else if (route.name === '검색') {
          iconSource = focused ? Search : SearchOutline;
        } else if (route.name === '지도') {
          iconSource = focused ? Location : LocationOutline;
        } else if (route.name === '찜') {
          iconSource = focused ? Heart : HeartOutline;
        } else if (route.name === '마이') {
          iconSource = focused ? Smaile : SmaileOutline;
        }

        return (
          <Image
            source={iconSource}
            // style={{tintColor: color, width: 24, height: 24}}
          />
        );
      },
    })}
    tabBarOptions={
      {
        // activeTintColor,
        // inactiveTintColor,
        // showLabel: true,
        // style: {backgroundColor: 'white'},
      }
    }>
    <Tab.Screen name="홈" component={HomePage} />
    <Tab.Screen name="검색" component={Temp} />
    <Tab.Screen name="지도" component={Temp} />
    <Tab.Screen name="찜" component={Temp} />
    <Tab.Screen name="마이" component={Temp} />
  </Tab.Navigator>
);

const BottomTabs = () => (
  <View style={{flex: 1}}>
    <CustomTabNavigator />
  </View>
);

export default BottomTabs;
