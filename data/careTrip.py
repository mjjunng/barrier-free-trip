from selenium import webdriver
from selenium.webdriver.common.by import By
import pymysql

# save db
def save_mysql_careTripService(db, data):
    conn = pymysql.connect(host='localhost',
                           user='root',
                           password='0422',
                           db=db,
                           charset='utf8')
    cursor = conn.cursor()
    sql = "insert into care_trip_service(id,title,tel,sido,sigungu,addr)" \
          "values(%s,%s,%s,%s,%s,%s)"

    cursor.execute(sql, data)
    conn.commit()
    conn.close()


driver = webdriver.Chrome()
url = "https://www.socialservice.or.kr:444/user/search/supply/supplyTotalList.do"

driver.get(url)
driver.find_element(By.ID, "p_ts_keyword").send_keys("돌봄여행 서비스")
driver.find_element(By.CLASS_NAME,"btn_srch").click()

x_paths = ['//*[@id="contents"]/div[2]/div[3]/div/span[1]', '//*[@id="contents"]/div[2]/div[3]/div/span[2]',
           '//*[@id="contents"]/div[2]/div[3]/div/span[3]', '//*[@id="contents"]/div[2]/div[3]/div/span[4]',
           '//*[@id="contents"]/div[2]/div[3]/div/span[5]', '//*[@id="contents"]/div[2]/div[3]/div/span[6]',
           '//*[@id="contents"]/div[2]/div[3]/div/span[7]', '//*[@id="contents"]/div[2]/div[3]/div/span[8]',
           '//*[@id="contents"]/div[2]/div[3]/div/span[9]', '//*[@id="contents"]/div[2]/div[3]/div/span[10]'
           # , '//*[@id="contents"]/div[2]/div[3]/div/span[11]', '//*[@id="contents"]/div[2]/div[3]/div/span[12]',
           # '//*[@id="contents"]/div[2]/div[3]/div/span[13]', '//*[@id="contents"]/div[2]/div[3]/div/span[14]',
           # '//*[@id="contents"]/div[2]/div[3]/div/span[15]'
            ]
idx = 0
for x_path in x_paths:
    driver.find_element(By.XPATH, x_path).click()
    page = driver.find_element(By.CLASS_NAME, 'srch_rsut')
    row = page.text.split("\n")[1:]

    for i in range(0, len(row), 3):
        row1 = row[i].split(" ")
        title = row1[0]
        tel = row1[1][5:][:-1]
        row2 = row[i+1].split(" ")

        sido = row2[-2]
        sigungu = row2[-1]
        addr = row[i+2]
        save_mysql_careTripService("barrier_free_trip", tuple([idx, title, tel, sido, sigungu, addr]))
        idx += 1
