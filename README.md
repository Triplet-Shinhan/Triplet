## Triplet 🐬

<p align="middle" >
<img src="https://github.com/Triplet-Shinhan/Triplet/assets/81220859/5e392a6e-fb33-4d1c-87c8-af8cbc22b208" width="400px;" height="150px;"/>
</p>

### <div align="center"> 🐬 여행 환전  🐬 </div>

- **프로젝트 명 :** Triplet
- **프로젝트 기간 :** 2023.9.1 ~ 2023.9.17
- **배포 링크 :** https://stalwart-crisp-e3127e.netlify.app/

## 소개 🔍
편한 환전과 함께하는 여행 금융 다이어리 

## 활용한 신한 API
우대율<br>
환전 신청<br>
환전가능 지점조회<br>
환율 조회<br>
환전 신청 결과 조회<br>
통화코드 목록 조회<br>
이체<br>
1원 이체<br>
거래내역조회<br>
푸쉬알람<br>

## 프로젝트 아키텍쳐 & 문서 🏛
<img src="https://github.com/Triplet-Shinhan/Triplet/assets/81220859/8aa887d7-bf54-4b2e-8145-5e0657d05566" width="900px;" height="350px;"/>

---

## 멤버
- Web BackEnd : 한영진, 김건우, 백승윤
- Web FrontEnd : 김예림
<div align="center">
  <a href="https://github.com/takealook97">
      <img src="https://github.com/takealook97.png/" width="100">
  </a>
  <a href="https://github.com/fangdol888">
      <img src="https://github.com/fangdol888.png/" width="100">
  </a>
  <a href="https://github.com/Ojin0104">
      <img src="https://github.com/Ojin0104.png/" width="100">
  </a>
  <a href="https://github.com/Lainlnya">
      <img src="https://github.com/Lainlnya.png/" width="100">
  </a>
</div>

---

## Triplet: 여행 금융 플래너
### 문제 인식
여행 시 지출을 정리하는 일은 생각보다 복잡하다. 환전을 통한 현금 지출, 카드를 통한 외화 지출은 대개 원화로 어림잡아 계산된다. 이렇게 대략적으로 계산한 금액은 정확하지 않다. 더욱이, 카드 지출의 환율은 변동성이 크므로 지출 시점마다 환율을 고려해야 한다. 그렇지 않으면, 실제 지출금액과 크게 다를 수 있다.

### Triplet이란?
Triplet은 'Trip'과 'Wallet'의 합성어로, "Let Trip" 이라는 의미를 담고 있다. 이는 '여행 금융 플래너'로, 여행 중 발생하는 다양한 지출을 효과적으로 관리해주는 도구다.

### 핵심 기능
- 환율 저장: 유저는 환전 시점의 환율을 저장해 현금 지출을 정확하게 기록한다.
- 카드 지출 자동 계산: 카드 지출 시점의 환율과 우대율을 고려해 자동으로 지출을 계산하며 누적한다.
- 외화 결제 구분: 카드로 해외 결제 시, 원화 결제와 외화 현지 통화 결제를 구분한다.
- 다이어리 방식 UI: 유저 친화적인 UI와 기능을 통해 누구나 쉽게 지출을 기록하고 관리할 수 있다.

---

## 주요 기술
### Language
- Java 11
- html, css, js

### Framework
- Springboot 2.7.10
- React 11.10.6

### DB
- MySQL 8.0.32
- S3
- 
### Server
- AWS EC2
- Docker

## ERD

![image](https://github.com/Triplet-Shinhan/Triplet/assets/118447769/0145506d-2a57-4729-b76b-01c0dbf7b408)

---

# 상세화면

## login
![image](https://github.com/Triplet-Shinhan/Triplet/assets/118447769/917769d5-b38e-46c3-8f22-a9869bd85f10)

## signup
![image](https://github.com/Triplet-Shinhan/Triplet/assets/118447769/73c248e1-e8fc-4a20-8c15-a124c8a9dbe6)

## project
![image](https://github.com/Triplet-Shinhan/Triplet/assets/118447769/177ef45d-fd3e-41cc-a0d5-7757850d3a31)


![image](https://github.com/Triplet-Shinhan/Triplet/assets/118447769/d75f6954-f934-44f1-8c5a-d9493089610f)

## daily
![image](https://github.com/Triplet-Shinhan/Triplet/assets/118447769/8bb13193-aee6-466b-a34a-fbee9d9a5180)

## specific

![image](https://github.com/Triplet-Shinhan/Triplet/assets/118447769/0cb115d4-7ff4-439a-a85b-2c9cac4ea012)
