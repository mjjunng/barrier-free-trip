import React from 'react';
import {Dimensions, Image, StyleSheet, View} from 'react-native';
import {Text} from 'react-native-paper';
import Swiper from 'react-native-swiper';
import LocationIcon from '../../public/detailPage/location.svg'
import HeartIcon from '../../public/detailPage/heart.svg'
interface DetailPageProps{
  data: DetailData;
  titile: string;
}
interface DetailData{
   contentId: string;
  contentTypeId: string;
  title: string;
  addr1: string;
  addr2: string;
  overview: string;
  homepage: string;
  tel: string;
  checkInTime: string;
  checkOutTime: string;
  parking: string;
  rating: number | null;
  areaCode: string;
  sigunguCode: string | null;
  mapx: string;
  mapy: string;
  imgs: string[];
  wheelchair: string | null;
  _exit: string | null;
  elevator: string | null;
  restroom: string | null;
  guidesystem: string | null;
  blindhandicapetc: string | null;
  signguide: string;
  videoguide: string | null;
  hearingroom: string | null;
  hearinghandicapetc: string | null;
  stroller: string | null;
  lactationroom: string | null;
  babysparechair: string | null;
  infantsfamilyetc: string | null;
  auditorium: string | null;
  room: string | null;
  handicapetc: string | null;
  braileblock: string | null;
  helpdog: string | null;
  guidehuman: string | null;
  audioguide: string | null;
  bigprint: string | null;
  brailepromotion: string | null;
  freeParking: string | null;
  route: string | null;
  publictransport: string | null;
  ticketoffice: string | null;
  promotion: string | null;
  like: number;
}

const styles = StyleSheet.create({
  container: {
    height: 300,
    width: Dimensions.get('window').width,
  },
  slide: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: 370,
    height: 300,
    borderRadius: 10,
  },
  swiperActiveDot: {
  backgroundColor: '#FAF9F9',
  width: 12,
  height: 12,
  borderRadius: 30,
  marginHorizontal: 4,
  },
  heart: {
    position: 'absolute',
    width: 52,
    height: 52,
    left: 276,
    top: 261,
  },
  infoOutline: {
    width: 360,
    height: 507,
    backgroundColor: '#FFF5F5',
    marginTop: 300,
  }
});

export const DetailPage = ({ data, title }: DetailPageProps) => {
  data = tempData; // 임시
  const images = data.imgs;

  return (
    <View style={styles.container}>
      <View>
        <Swiper
        activeDotStyle={styles.swiperActiveDot}>
        {images.map((image, index) => (
            <View key={index} style={styles.slide}>
              <Image source={{ uri: image }} style={styles.image} resizeMode="cover" />
            </View>
          ))}
        </Swiper>
        <View style={styles.heart} >
            <HeartIcon/>
        </View>
      </View>

      <View style={styles.infoOutline}>
      </View>
        
    </View>
  );
};

export default DetailPage;

// export const DetailPage = ({ data, title }: DetailPageProps) => {
//   data = tempData; // 임시
//   const images = data.imgs;

//   console.log('Detail Data: ', data.imgs);

//   return (
//     <View style={styles.container}>
//       <Swiper
//         style={styles.swiper}
//         containerStyle={styles.swiperContainer}
//         // dotStyle={styles.swiperDot}
//         // activeDotStyle={styles.swiperActiveDot}
//         width={344}
//         height={300}
//       >
//         {images.map((image, index) => (
//           <View key={index} style={styles.slide}>
//             <Image source={{ uri: image }} style={styles.img} resizeMode="cover" />
//           </View>
//         ))}
//       </Swiper>
//     </View>
//   );
// }

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     marginLeft: 12,
//     marginRight: 12,
//     alignItems: 'center',
//     justifyContent: 'center',
//   },
//   swiper: {
//     width: '100%',
//     height: '100%',
//     // width: '100%',
//     // height: '100%',
//     // borderRadius: 10,
//   },
//   swiperContainer: {
//     // height: 300,
//     borderRadius: 10,
//   },
//   swiperDot: {
//     backgroundColor: '#C4C4C4',
//     width: 8,
//     height: 8,
//     borderRadius: 4,
//     marginHorizontal: 4,
//   },
//   swiperActiveDot: {
//     backgroundColor: '#FF6B6B',
//     width: 16,
//     height: 8,
//     borderRadius: 4,
//     marginHorizontal: 4,
//   },
//   slide: {
//     flex: 1,
//     alignItems: 'center',
//     justifyContent: 'center',
//   },
//   img: {
    
//     resizeMode: 'cover',
//     borderRadius: 10,
//   },
// });
const tempData = {
  contentId: '250279',
  contentTypeId: '12',
  title: '둔산선사유적지',
  addr1: '대전광역시 서구 대덕대로317번길',
  addr2: '(월평동)',
  overview:
    '* 구석기와 신석기 유적이 한 곳에, 둔산선사유적지 *\n대전광역시 기념물인 둔산선사유적지는 우리나라에서는 처음으로 한 곳에서 구석기·신석기·청동기시대의 유적이 발굴된 곳이다. 이곳은 둔산지구 개발 사업으로 인하여 1991년에 발견된 유적이다. 조사 결과 청동기시대 집 자리 3기와 신석기시대의 움집자리, 용도를 알 수 없는 구덩이와 빗살무늬토기조각 등의 유물이 발견되었고 구석기시대의 석기 50여 점도 발견되었다. 대전지역에서 마을이 이루어지는 단서를 제공한 곳이 바로 이곳이다. 이 유적은 이전복원 후에 선사유적공원으로 조성되었다. 구석기시대의 유적은 약 2백평 정도의 면적에서 몸돌, 망치돌 등 50여 점의 석기와 석기를 만들 때 떨어져 나온 작은 부스러기들이 나왔다. 이들 유물 가운데 쌍날 찍개, 긁개, 밀개 등은 유물의 형태와 떼어낸 수법으로 보아 후기구석기의 이른 시기이거나 중기구석기의 늦은 시기 유물로 보인다. 신석기시대의 유적은 지름 2∼3m 내외, 깊이 0.8∼1.3m 정도의 작은 집터 유적 13기가 조사되었으며, 빗살무늬토기 조각·보습·갈돌·어망추 등이 나왔다. 이곳의 신석기시대 유적은 신석기시대 후기에 속하며 문화 계통으로는 서해안의 빗살무늬 토기 문화 전통이 강하고 함경도 해안지역의 문화요소도 일부 보인다. 청동기시대의 유적에서는 3기의 집자리 유적이 조사되었으며 팽이형 민무늬 토기 조각, 방추차, 돌도끼, 돌화살촉, 돌칼, 숫돌 등이 나왔다. 둔산지역의 선사유적은 이 지역에서 갑천 주변의 얕은 구릉에 넓은 농토와 풍부한 물을 배경으로 많은 주민이 살았던 사실을 알려주며, 대전의 선사문화 갈래와 계통을 확인하는데 좋은 자료로 평가되고 있다.\n* 둔산선사유적지의 의의 *\n둔산선사유적지는 한국에서는 처음으로 한곳에서 구석기·신석기·청동기시대의 유물이 한꺼번에 발견되었다는 역사적 의의를 지니며, 이곳 갑천(甲川) 유역이 얕은 구릉과 넓은 농토, 풍부한 물을 배경으로 선사시대부터 많은 인류가 생활했던 곳임을 알 수 있다. 또한, 부근의 괴정동 청동기유적,석장리 구석기유적 등과 함께 이 지방의 선사문화의 갈래와 계통을 확인하는 데 중요한 자료로 평가된다.',
  homepage: '',
  tel: '',
  checkInTime: '',
  checkOutTime: '',
  parking: '',
  rating: null,
  areaCode: '3',
  sigunguCode: null,
  mapx: '127.3783794582',
  mapy: '36.3605282406',
  imgs: [
    'http://tong.visitkorea.or.kr/cms/resource/76/1092676_image2_1.jpg',
    'http://tong.visitkorea.or.kr/cms/resource/77/1092677_image2_1.jpg',
    'http://tong.visitkorea.or.kr/cms/resource/79/1092679_image2_1.jpg',
    'http://tong.visitkorea.or.kr/cms/resource/83/1092683_image2_1.jpg',
    'http://tong.visitkorea.or.kr/cms/resource/12/1585612_image2_1.jpg',
    'http://tong.visitkorea.or.kr/cms/resource/15/1585615_image2_1.jpg',
    'http://tong.visitkorea.or.kr/cms/resource/16/1585616_image2_1.jpg',
  ],
  wheelchair: null,
  _exit: null,
  elevator: null,
  restroom: null,
  guidesystem: null,
  blindhandicapetc: null,
  signguide: '3',
  videoguide: null,
  hearingroom: null,
  hearinghandicapetc: null,
  stroller: null,
  lactationroom: null,
  babysparechair: null,
  infantsfamilyetc: null,
  auditorium: null,
  room: null,
  handicapetc: null,
  braileblock: null,
  helpdog: null,
  guidehuman: null,
  audioguide: null,
  bigprint: null,
  brailepromotion: null,
  freeParking: null,
  route: null,
  publictransport: null,
  ticketoffice: null,
  promotion: null,
  like: 0,
};
