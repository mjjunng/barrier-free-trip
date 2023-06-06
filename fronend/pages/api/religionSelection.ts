import axios from 'axios';
// import {VWORLD_KEY} from 'react-native-dotenv';
import dotenv from 'dotenv';

export async function getSidoCode(): Promise<any> {
  return await axios
    .get('https://api.vworld.kr/req/data', {
      params: {
        // key: '40F5AD59-571A-3D84-B5A6-562C8453F64F',
        key: process.env.VWORLD_KEY,
        service: 'data',
        version: '2.0',
        request: 'getfeature',
        format: 'json',
        size: 10,
        page: 1,
        geometry: false,
        attribute: true,
        crs: 'EPSG:3857',
        geomfilter:
          'BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)',
        data: 'LT_C_ADSIDO_INFO',
      },
    })
    .then(res => res.data)
    .catch(error => error);
}
