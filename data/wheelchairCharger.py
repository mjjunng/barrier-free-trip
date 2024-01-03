import json
import pymysql

def saveMysqlCarger(data, cursor, conn):
    sql = "insert into wheelchairCharger(id, title, sido, sigungu, areaCode, addr, weekdayOpen, " \
          "weekdayClose, weekendOpen, weekendClose, holidayOpen, holidayClose, possible, air, phoneCharge, tel, " \
          "mapx, mapy) " \
          "values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"

    cursor.execute(sql, data)
    conn.commit()
    conn.close()

def saveWheelchairCharger(host, user, password, db, charset):
    conn = pymysql.connect(host=host,
                           user=user,
                           password=password,
                           db=db,
                           charset=charset)
    cursor = conn.cursor()

    file_path = "wheelchairChargerData.json"

    idx = 0
    with open(file_path, 'r', encoding='utf-8') as file:
        json_data = json.load(file)
        data = json_data["records"]

        for d in data:
            title = d["시설명"]
            sido = d["시도명"]
            sigungu = d["시군구명"]
            areaCode = d["시군구코드"]
            addr = d["소재지도로명주소"]
            addr2 = d["소재지지번주소"]
            weekdayOpen = d["평일운영시작시각"]
            weekdayClose = d["평일운영종료시각"]
            weekendOpen = d["토요일운영시작시각"]
            weekendClose = d["토요일운영종료시각"]
            holidayOpen = d["공휴일운영시작시각"]
            holidayClose = d["공휴일운영종료시각"]
            possible = d["동시사용가능대수"]
            air = d["공기주입가능여부"]
            phoneCharge = d["휴대전화충전가능여부"]
            tel = d["관리기관전화번호"]
            mapx = d["경도"]
            mapy = d["위도"]

            if not addr:
                addr = addr2

            saveMysqlCarger(tuple([idx, title, sido, sigungu, areaCode, addr, weekdayOpen,
                                                          weekdayClose, weekendOpen, weekendClose, holidayOpen, holidayClose,
                                                          possible, air, phoneCharge, tel, mapx, mapy]), cursor, conn)
            idx += 1
