# Triply

Triply는 숙박시설과 관광지, 음식점의 **무장애 편의 시설 정보**를
제공하여 장애인들이 편리하게 여행을 즐길 수 있도록 돕습니다.
<br></br>
### 목차
- [서비스 소개](#서비스-소개)

- [백엔드 디렉터리 구조](#백엔드-디렉터리-구조)

- [ERD](#erd)

- [회의자료 및 API 명세서](#회의자료-및-api-명세서)
<br></br>
### **서비스 소개**

- 숙박, 관광지, 음식점의 기본 정보 및 무장애 편의정보 제공
- 돌봄여행, 충전소, 렌탈 서비스 제공처의 위치 정보 제공
- 검색 기능
- 여행 코스 생성 → 2차 개발 예정
- 관심있는 장소 찜 기능
- 리뷰 서비스

### 개발 환경

- Java11
- Spring Boot 2.7, Gradle
- MySQL 8.0.33
- Docker Desktop
- Android Studio

### 백엔드 디렉터리 구조
```markdown
├─caretrip
│  ├─controller
│  ├─domain
│  ├─dto
│  ├─repository
│  └─service
├─charger
│  ├─controller
│  ├─domain
│  ├─dto
│  ├─repository
│  └─service
├─heart
│  └─controller
├─map
│  ├─controller
│  ├─domain
│  ├─repository
│  └─service
├─member
│  ├─controller
│  ├─domain
│  ├─dto
│  ├─repository
│  └─service
├─rental
│  ├─controller
│  ├─domain
│  ├─dto
│  ├─repository
│  └─service
├─review
│  ├─controller
│  ├─domain
│  ├─dto
│  ├─repository
│  └─service
├─search
│  ├─controller
│  ├─dto
│  ├─repository
│  └─service
└─touristfacility
    ├─controller
    ├─domain
    ├─dto
    ├─repository
    └─service
```
### ERD

![barrier-free-trip](https://github.com/mjjunng/barrier-free-trip/assets/52596617/ee800c06-b2a4-4261-b9d2-42460afcfa5e)

### 회의자료 및 API 명세서

🔗 [https://www.notion.so/BFT-42486d4e0c87402face4081874fa9554?pvs=4](https://www.notion.so/BFT-42486d4e0c87402face4081874fa9554?pvs=21)
