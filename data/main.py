import createTable
import touristFacility
import rental
import careTrip
import wheelchairCharger

# 0. setting
host = 'localhost'
user = 'root'
password = '0422'
db = 'barrier_free_trip'
charset = 'utf8'

# 1. 테이블이 있는 지 확인한 후, 테이블이 없으면 테이블 create
exits = createTable.checkExits(host, user, password, db, charset, 'touristFacility')
if exits == 0:
    createTable.createTables(host, user, password, db, charset)

# 2. 관광시설 데이터 저장
touristFacility.saveTouristFacility(host, user, password, db, charset)

# 3. 렌탈 서비스 데이터 저장
rental.saveRental(host, user, password, db, charset)

# 4. 돌봄 여행 서비스 데이터 저장
careTrip.saveCareTrip(host, user, password, db, charset)

# 5. 전동휠체어 충전소 데이터 저장
wheelchairCharger.saveWheelchairCharger(host, user, password, db, charset)

print("=======> finish save!!! <======")