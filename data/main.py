import initTable
import touristFacility
import rental
import careTrip
import wheelchairCharger
import os

# 0. setting
host = os.environ['host']
user = os.environ['user']
password = os.environ['password']
db = 'barrier_free_trip'
charset = 'utf8'

tables = ['touristFacility', 'barrierFreeFacility', 'touristFacilityImg',
          'careTripService', 'rental', 'wheelchairCharger']

# 1. 테이블이 있으면 drop 후 create
for table in tables:
    initTable.dropTables(host, user, password, db, charset, table)

initTable.createTables(host, user, password, db, charset)

# 2. 관광시설 데이터 저장
touristFacility.saveTouristFacility(host, user, password, db, charset)

# 3. 렌탈 서비스 데이터 저장
rental.saveRental(host, user, password, db, charset)

# 4. 돌봄 여행 서비스 데이터 저장
careTrip.saveCareTrip(host, user, password, db, charset)

# 5. 전동휠체어 충전소 데이터 저장
wheelchairCharger.saveWheelchairCharger(host, user, password, db, charset)

print("=======> finish save!!! <======")