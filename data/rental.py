from selenium import webdriver
from selenium.webdriver.common.by import By
import pymysql

# save db
def saveMysqlRentalService(data, cursor, conn):

    sql = "insert ignore into rental(id,title,tel,sido,sigungu,addr)" \
          "values(%s,%s,%s,%s,%s,%s)"

    cursor.execute(sql, data)
    conn.commit()
    conn.close()


def saveRental(host, user, password, db, charset):
    conn = pymysql.connect(host=host,
                           user=user,
                           password=password,
                           db=db,
                           charset=charset)
    cursor = conn.cursor()

    driver = webdriver.Chrome()
    url = "https://www.socialservice.or.kr:444/user/search/supply/supplyTotalList.do"

    driver.get(url)
    driver.find_element(By.ID, "p_ts_keyword").send_keys("장애인 보조기기 렌탈서비스")
    driver.find_element(By.CLASS_NAME, "btn_srch").click()

    x_paths = ['//*[@id="contents"]/div[2]/div[3]/div/span[1]', '//*[@id="contents"]/div[2]/div[3]/div/span[2]',
               '//*[@id="contents"]/div[2]/div[3]/div/span[3]', '//*[@id="contents"]/div[2]/div[3]/div/span[4]',
               '//*[@id="contents"]/div[2]/div[3]/div/span[5]', '//*[@id="contents"]/div[2]/div[3]/div/span[6]',
               '//*[@id="contents"]/div[2]/div[3]/div/span[7]', '//*[@id="contents"]/div[2]/div[3]/div/span[8]',
               '//*[@id="contents"]/div[2]/div[3]/div/span[9]'
                ]
    idx = 0

    for x_path in x_paths:
        driver.find_element(By.XPATH, x_path).click()
        page = driver.find_element(By.CLASS_NAME, 'srch_rsut')
        row = page.text.split("\n")[1:]

        for i in range(0, len(row), 3):
            row1 = row[i]
            title = row1
            tel = ""
            if '(연락처' in row1:
                pos = row1.index('(연락처')
                title = row1[:pos]
                tel = row1[pos + 5:-1]

            addr = row[i+2]
            sido = addr.split(" ")[0]
            sigungu = addr.split(" ")[1]

            saveMysqlRentalService(tuple([idx, title, tel, sido, sigungu, addr]), cursor, conn)
            idx += 1
