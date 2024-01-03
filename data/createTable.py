import pymysql

def checkExits(host, user, password, db, charset, tableName):
    conn = pymysql.connect(host=host,
                           user=user,
                           password=password,
                           db=db,
                           charset=charset)
    cursor = conn.cursor()
    sql = 'show table like ' + tableName
    cursor.execute(sql)
    result = cursor.fetchall()
    conn.commit()
    conn.close()

    return result

def createTables(host, user, password, db, charset):
    conn = pymysql.connect(host=host,
                           user=user,
                           password=password,
                           db=db,
                           charset=charset)
    cursor = conn.cursor()

    sql1 = '''CREATE TABLE `touristFacility` ( 
            `contentId`	varchar(30)	NOT NULL PRIMARY KEY,
            `contentTypeId`	varchar(30) NOT NULL,
            `title`	text	NOT NULL,
            `addr1`	text	NOT NULL,
            `addr2`	varchar(100)	NULL,
            `overview`	longtext	NULL,
            `homepage`	text	NULL,
            `tel`	varchar(50)	NULL,
            `checkInTime`	varchar(50)	NULL,
            `checkOutTime`	varchar(50)	NULL,
            `parking`	text	NULL,
            `rating`	long	NULL,
            `areaCode`	varchar(30)	NOT NULL,
            `sigunguCode`	varchar(30)	NOT NULL,
            `mapx`	varchar(50)	NULL,
            `mapy`	varchar(50)	NULL,
            `firstimage` varchar(300) NULL
            )'''

    sql2 = '''CREATE TABLE `barrierFreeFacility` (
            `contentId`	varchar(10)	NOT NULL PRIMARY KEY,
            `contentTypeId`	varchar(10)	NULL,
            `wheelchair`	varchar(100)	NULL,
            `_exit`	text	NULL,
            `elevator`	varchar(100)	NULL,
            `restroom`	varchar(100)	NULL,
            `guidesystem`	varchar(100)	NULL,
            `blindhandicapetc`	varchar(100)	NULL,
            `signguide`	varchar(100)	NULL,
            `videoguide`	varchar(100)	NULL,
            `hearingroom`	varchar(100)	NULL,
            `hearinghandicapetc`	varchar(100)	NULL,
            `stroller`	varchar(100)	NULL,
            `lactationroom`	varchar(100)	NULL,
            `babysparechair`	varchar(100)	NULL,
            `infantsfamilyetc`	varchar(100)	NULL,
            `auditorium`	varchar(100)	NULL,
            `room`	varchar(100)	NULL,
            `handicapetc`	varchar(100)	NULL,
            `braileblock`	varchar(100)	NULL,
            `helpdog`	varchar(100)	NULL,
            `guidehuman`	varchar(100)	NULL,
            `audioguide`	varchar(100)	NULL,
            `bigprint`	varchar(100)	NULL,
            `brailepromotion`	varchar(100)	NULL,
            `freeParking`	text	NULL,
            `route`	varchar(100)	NULL,
            `publictransport`	varchar(100)	NULL,
            `ticketoffice`	varchar(100)	NULL,
            `promotion`	varchar(100)	NULL
            )'''

    sql3 = '''CREATE TABLE `touristFacilityImg` (
            `serialnum`	varchar(50)	NOT NULL PRIMARY KEY,
            `contentId`	varchar(10)	NOT NULL,
            `cpyrhtDivCd`	varchar(10)	NOT NULL,
            `originImgurl`	varchar(300) NOT NULL
             )'''

    sql4 = '''CREATE TABLE `careTripService` (
            `id`	int NOT NULL primary key,
            `title`	varchar(100)	NULL,
            `addr`	varchar(100)	NULL,
            `tel`	varchar(50)	NULL,
            `sido`	varchar(20)	NULL,
            `sigungu`	varchar(20)	NULL,
            unique index(title)
            )'''


    sql5 = '''CREATE TABLE `rental` (
            `id`	int NOT NULL primary key,
            `title`	varchar(100)	NULL,
            `addr`	varchar(100)	NULL,
            `tel`	varchar(50)	NULL,
            `sido`	varchar(20)	NULL,
            `sigungu`	varchar(20)	NULL, 
            unique index(title)
            )'''

    sql6 = '''CREATE TABLE `wheelchairCharger` (
            `id`	int	NOT NULL primary key,
            `title`	varchar(100)	NULL,
            `sido`	varchar(20)	NULL,
            `sigungu`	varchar(20)	NULL,
            `areaCode`	varchar(20)	NULL,
            `addr`	varchar(200)	NULL,
            `weekdayOpen`	varchar(20)	NULL,
            `weekdayClose`	varchar(20)	NULL,
            `weekendOpen`	varchar(20)	NULL,
            `weekendClose`	varchar(20)	NULL,
            `holidayOpen`	varchar(20)	NULL,
            `holidayClose`	varchar(20)	NULL,
            `possible`	int	NULL,
            `air`	varchar(5)	NULL,
            `phoneCharge`	varchar(5)	NULL,
            `tel`	varchar(50)	NULL,
            `mapx`	varchar(50)	NULL,
            `mapy`	varchar(50)	NULL
            )'''

    cursor.execute(sql1)
    cursor.execute(sql2)
    cursor.execute(sql3)
    cursor.execute(sql4)
    cursor.execute(sql5)
    cursor.execute(sql6)
    conn.commit()
    conn.close()