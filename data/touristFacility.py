import requests
import json
import pymysql

serviceKey = "x80%2Fkb1fC2Bm2WJYIVN%2BvX%2Blx2CQEs5rB6fQyz6jRL359DglzzDBeKCjZ2jkyvjaVpinPrXRgDOpRCbVIf1k6A%3D%3D"

# save db
def save_mysql_tourist(db, data):
    conn = pymysql.connect(host='localhost',
                           user='root',
                           password='0422',
                           db=db,
                           charset='utf8')
    cursor = conn.cursor()
    sql = "insert ignore into tourist_facility(contentId,contentTypeId,title,addr1,addr2,overview,homepage,tel," \
          "checkInTime,checkOutTime,parking,areaCode,sigunguCode,mapx,mapy,firstimage) " \
          "values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    cursor.execute(sql, data)
    conn.commit()
    conn.close()

def save_mysql_barrier_free(db, data):
    conn = pymysql.connect(host='localhost',
                           user='root',
                           password='0422',
                           db=db,
                           charset='utf8')
    cursor = conn.cursor()
    sql = "insert ignore into barrier_free_facility(contentId,wheelchair,_exit,elevator,restroom,guidesystem," \
                                        "blindhandicapetc,signguide,videoguide,hearingroom,hearinghandicapetc," \
                                        "stroller,lactationroom,babysparechair,infantsfamilyetc,auditorium," \
                                        "room,handicapetc,braileblock,helpdog,guidehuman,audioguide,bigprint," \
                                        "brailepromotion,freeParking,route,publictransport,ticketoffice,promotion) " \
                                        "values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," \
                                        "%s,%s,%s,%s,%s,%s,%s,%s,%s)"

    cursor.execute(sql, data)
    conn.commit()
    conn.close()

def save_mysql_tourist_img(db, data):
    conn = pymysql.connect(host='localhost',
                           user='root',
                           password='0422',
                           db=db,
                           charset='utf8')
    cursor = conn.cursor()
    sql = "insert ignore into tourist_facility_img(contentId, originImgurl, cpyrhtDivCd, serialnum) " \
          "values(%s,%s,%s,%s)"

    cursor.execute(sql, data)
    conn.commit()
    conn.close()

# 관광 시설 정보 저장
contentTypeIds = ['12', '32', '39']
areaCodes = [str(i) for i in range(1, 9)] + ['31', '32']
sigunguCodes = [[str(i) for i in range(1, 11)], [str(i) for i in range(1, 11)], [str(i) for i in range(1, 6)],
                [str(i) for i in range(1, 9)], [str(i) for i in range(1, 6)], [str(i) for i in range(1, 11)],
                [str(i) for i in range(1, 11)], [str(i) for i in range(1, 2)], [str(i) for i in range(1, 11)],
                [str(i) for i in range(1, 11)]]

tourist_lst = []
barrier_free_lst = []
tourist_img_lst = []

cnt1 = 0
cnt2 = 0

for i in range(len(contentTypeIds)):
    for j in range(len(areaCodes)):
        for k in range(len(sigunguCodes[j])):

            url1 = "https://apis.data.go.kr/B551011/KorWithService1/areaBasedList1?MobileOS=WIN&MobileApp=barrir-free-trip" \
                   "&serviceKey=" + serviceKey + \
                   "&_type=json" \
                   "&contentTypeId=" + contentTypeIds[i] + \
                   "&areaCode=" + areaCodes[j] + \
                   "&sigunguCode=" + sigunguCodes[j][k]

            try:
                res = requests.get(url1).text
                data = json.loads(res)
                resultCode = data["response"]["header"]["resultCode"]
                res_cnt1 = int(data["response"]["body"]["totalCount"])

                contentId = ""
                contentTypeId = ""
                title = ""
                addr1 = ""
                addr2 = ""
                tel = ""
                areaCode = areaCodes[j]
                sigunguCode = sigunguCodes[j][k]
                mapx = ""
                mapy = ""
                firstimage = ""
                parking = ""
                checkInTime = ""
                checkOutTime = ""
                homepage = ""
                overview = ""

                if resultCode == "0000" and 0 < res_cnt1:
                    items = data["response"]["body"]["items"]["item"]
                    for item in items:
                        contentId = item['contentid']
                        contentTypeId = contentTypeIds[i]
                        title = item['title']
                        addr1 = item['addr1']
                        addr2 = item['addr2']
                        tel = item['tel']
                        areaCode = areaCodes[j]
                        sigunguCode = sigunguCodes[j][k]
                        mapx = item['mapx']
                        mapy = item['mapy']
                        firstimage = item['firstimage']

                        # 상세 정보
                        url2 = 'https://apis.data.go.kr/B551011/KorWithService1/detailCommon1?MobileOS=WIN' \
                               '&MobileApp=barrier-free-trip' \
                               '&contentId=' + contentId + \
                               '&overviewYN=Y' \
                               '&_type=json' \
                               '&contentTypeId=' + contentTypeId + \
                               '&serviceKey=' + serviceKey

                        res = requests.get(url2).text

                        try:
                            data = json.loads(res)
                            resultCode = data["response"]["header"]["resultCode"]
                            cnt = int(data["response"]["body"]["totalCount"])


                            if resultCode == "0000" and 0 < cnt:
                                item = data["response"]["body"]["items"]["item"][0]
                                overview = item['overview']
                        except Exception as e:
                            print("error: ", e)
                            print("url2: ", url2)

                        # 상세 정보2
                        url3 = 'https://apis.data.go.kr/B551011/KorWithService1/detailIntro1?MobileOS=WIN&MobileApp=barrier-free-trip' \
                               '&contentId=' + contentId + \
                               '&contentTypeId=' + contentTypeId + \
                               '&_type=json' \
                               '&serviceKey=' + serviceKey

                        # print("url3: ", url3)
                        res = requests.get(url3).text
                        try:
                            data = json.loads(res)
                            resultCode = data["response"]["header"]["resultCode"]
                            cnt = int(data["response"]["body"]["totalCount"])

                            if resultCode == "0000" and 0 < cnt:
                                item = data["response"]["body"]["items"]["item"][0]

                                try:
                                    parking = item['parking']

                                except:
                                    pass

                                if contentTypeId == '32':
                                    checkInTime = item['checkintime']
                                    checkOutTime = item['checkouttime']
                                    homepage = item['reservationurl']

                            save_mysql_tourist("barrier_free_trip", tuple([contentId, contentTypeId, title, addr1, addr2, overview,
                                                  homepage, tel, checkInTime, checkOutTime, parking, areaCode,
                                                  sigunguCode, mapx, mapy, firstimage]))
                            cnt1 += 1

                        except Exception as e:
                            print("error: ", e)
                            print("url3: ", url3)

                        # 무장애 정보
                        url4 = 'https://apis.data.go.kr/B551011/KorWithService1/detailWithTour1?MobileOS=WIN' \
                               '&MobileApp=barrier-free-trip' \
                               '&contentId=' + contentId + \
                               '&_type=json' \
                               '&serviceKey=' + serviceKey

                        res = requests.get(url4).text
                        try:
                            data = json.loads(res)
                            resultCode = data["response"]["header"]["resultCode"]
                            res_cnt = int(data["response"]["body"]["totalCount"])

                            if resultCode == "0000" and 0 < res_cnt:
                                item = data["response"]["body"]["items"]["item"][0]

                                wheelchair = item['wheelchair']
                                exit = item['exit']
                                elevator = item['elevator']
                                restroom = item['restroom']
                                guidesystem = item['guidesystem']
                                blindhandicapetc = item['blindhandicapetc']
                                signguide = item['signguide']
                                videoguide = item['videoguide']
                                hearingroom = item['hearingroom']
                                hearinghandicapetc = item['hearinghandicapetc']
                                stroller = item['stroller']
                                lactationroom = item['lactationroom']
                                babysparechair = item['babysparechair']
                                infantsfamilyetc = item['infantsfamilyetc']
                                auditorium = item['auditorium']
                                room = item['room']
                                handicapetc = item['handicapetc']
                                braileblock = item['braileblock']
                                helpdog = item['helpdog']
                                guidehuman = item['guidehuman']
                                audioguide = item['audioguide']
                                bigprint = item['bigprint']
                                brailepromotion = item['brailepromotion']
                                freeParking = item['parking']
                                route = item['route']
                                publictransport = item['publictransport']
                                ticketoffice = item['ticketoffice']
                                promotion = item['promotion']

                                save_mysql_barrier_free("barrier_free_trip",  tuple([contentId, wheelchair, exit, elevator, restroom, guidesystem,
                                           blindhandicapetc, signguide, videoguide, hearingroom, hearinghandicapetc,
                                           stroller, lactationroom, babysparechair, infantsfamilyetc, auditorium,
                                           room, handicapetc, braileblock, helpdog, guidehuman, audioguide, bigprint,
                                           brailepromotion, freeParking, route, publictransport, ticketoffice,
                                           promotion]))

                                cnt2 += 1
                        except Exception as e:
                            print("error: ", e)
                            print("url4: ", url4)
                        # 이미지
                        url5 = 'https://apis.data.go.kr/B551011/KorWithService1/detailImage1?MobileOS=WIN' \
                               '&MobileApp=barrier-free-trip' \
                               '&contentId=' + contentId + \
                               '&imageYN=Y' \
                               '&subImageYN=Y' \
                               '&_type=json' \
                               '&serviceKey=' + serviceKey
                        try:
                            res = requests.get(url5).text
                            data = json.loads(res)
                            resultCode = data["response"]["header"]["resultCode"]
                            cnt = int(data["response"]["body"]["totalCount"])
                            originImgurl = ""
                            cpyrhtDivCd = ""
                            serialnum = ""

                            if resultCode == "0000" and 0 < cnt:
                                items = data["response"]["body"]["items"]["item"]
                                for item in items:
                                    originImgurl = item['originimgurl']
                                    cpyrhtDivCd = item['cpyrhtDivCd']
                                    serialnum = item['serialnum']

                                    save_mysql_tourist_img("barrier_free_trip", tuple([contentId, originImgurl, cpyrhtDivCd, serialnum]))

                        except Exception as e:
                            print("error: ", e)
                            print("url5: ", url5)

            except Exception as e:
                print("error: ", e)
                print("url1: ", url1)
#print(cnt1)
#print(cnt2)
