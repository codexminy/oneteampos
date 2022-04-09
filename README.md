# :computer: 카페 포스(POS) 프로그램
<br>

> ## 소개
:pushpin: 카운터(계산, 할인&적립 등), 메뉴 관리, 매출 관리, 발주, 사원 관리, 회원 관리 등 **카페 운영을 전반적으로 관리해주는 POS 프로그램**
<br>
:pushpin: **자바 GUI(스윙)** 를 활용하여 프로그램 개발

<br>

> ## 개발 기간
#### :pushpin: 3주
 * **1주차** : 프로젝트 기획, DB 설계
 * **2주차** : 화면&기능구현
 * **3주차** : 화면&기능구현, 테스트
<br>

> ## 참여 인원
#### :pushpin: 4명
<br>

> ## 개발환경
#### :pushpin: 버전관리
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

#### :pushpin: DBMS/Back-end
<img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">

#### :pushpin: IDE
<img src="https://img.shields.io/badge/eclipse ide-2C2255?style=for-the-badge&logo=eclipse ide&logoColor=white">
<br>

> ## ERD

![image](https://user-images.githubusercontent.com/85227582/162565008-69d2ed15-1de1-4612-bc3f-6fbe9a623969.png)
<br>
<br>
> ## 프로그램 실행 화면
<br>

## :pushpin: 로그인
```
* 번호를 누르고 비밀번호가 맞으면 로그인이 자동으로 되도록 설정했습니다.
* 매니저급 이상만 로그인 가능하도록 설정했습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162574637-cbbb08c6-2b40-4dcf-88ad-a475c0ed8ee7.png)
<br>

## :pushpin: 메인
```
* 로그인을 하게 되면 나오는 메뉴 패널입니다.
* 각 버튼들을 누르면 원하는 파트로 넘어가도록 설정했습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162574720-7ad56589-92f0-4b89-acb9-fbf96655f2dc.png)
<br>

## :pushpin: 메뉴
```
* 기본 포맷으로는 로그인 정보와 현재 시간을 보여주는 시계, 뒤로가기 버튼이 들어가 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162574798-9810e7dc-5078-47c6-952a-aed7ddfaa3a7.png)

---
### :point_down: 메뉴 관리
```
* 메뉴 관리를 누르면 프레임창이 생성됩니다.
* 프레임창에서 메뉴 검색, 메뉴 수정, 메뉴 등록, 메뉴 삭제를 할 수 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162575098-000053e9-31d0-429f-bf60-f9f03c95557a.png)
<br>
<br>
<br>

---
### :point_down: 메뉴 상세 선택
```
* 메뉴를 클릭하면 메뉴 상세 선택 창이 생성됩니다.
* 온도, 사이즈, 추가 메뉴, 수량을 선택할 수 있습니다.
* 추가를 클릭하면 오른쪽 장바구니에 상품이 담기게 됩니다.
```
![image](https://user-images.githubusercontent.com/85227582/162575366-32cc8cff-3ae8-4d4d-906a-4ec7912b4c35.png)
<br>
<br>
<br>

---
### :point_down: 회원 할인&적립
```
* 결제 전에 회원조회를 통해 할인&적립을 적용할 수 있고, 할인&적립을 취소할 수도 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162576259-c0fd7b46-d49a-4658-9f8b-5207221d4827.png)
<br>
<br>
<br>

---
### :point_down: 결제
```
* 결제로 넘어가면 카드와 현금으로 구분해서 결제가 가능합니다.
```
![image](https://user-images.githubusercontent.com/85227582/162577145-33641d55-382d-43ae-a2fb-a56e2c65a169.png)
<br>
<br>
<br>

---
### :point_down: 영수증
```
* 영수증을 클릭하면 결제된 목록들을 볼 수 있습니다.
* 테이블을 클릭해서 반품이나 출력할 결제를 선택할 수 있습니다.
* 반품시에는 결제하면서 적립됐던 포인트나 누적금액을 제외시킵니다.
```
![image](https://user-images.githubusercontent.com/85227582/162577250-70279b63-1b5e-474b-8abf-0e3dacafbbae.png)
<br>
<br>
<br>
