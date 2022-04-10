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
 * **권이슬** : 회원
 * **김민지** : 매출, 발주, 사원
 * **송경원** : 메뉴
 * **이재선** : 매출
<br>

> ## 개발환경
#### :pushpin: 버전관리
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

#### :pushpin: DBMS/BACK-END
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
* 로그인을 하게 되면 나오는 메뉴입니다.
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
![image](https://user-images.githubusercontent.com/85227582/162578338-b2459e30-f710-4ac1-b66c-c8b13e9cb8f9.png)
<br>
<br>
<br>

---
### :point_down: 영수증
```
* 영수증을 클릭하면 결제된 목록들을 볼 수 있습니다.
* 테이블을 클릭해서 반품이나 출력할 결제를 선택할 수 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162577250-70279b63-1b5e-474b-8abf-0e3dacafbbae.png)
<br>
<br>
<br>

---
### :point_down: 영수증(반품)
```
* 반품시에는 결제하면서 적립됐던 포인트나 누적금액을 제외시킵니다.
```
![image](https://user-images.githubusercontent.com/85227582/162577575-fe4d197f-ef6b-45ce-aade-d69132cb1ba2.png)
<br>
<br>
<br>
<br>
<br>
<br>
<br>

## :pushpin: 매출
```
* 매출에 들어가게 되면 첫 번째로 일매출과 월매출로 나눠서 살펴볼 수 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162577730-76d64cd5-cfb7-4b1a-a374-f4a0bf094036.png)

---
### :point_down: 일매출
```
* 기간 설정은 달력 또는 1개월, 3개월, 6개월 버튼으로 가능합니다.
* 달력은 콤보박스나 양 옆의 버튼을 통해서 년, 월을 움직일 수 있습니다.
* 기간 설정을 한 뒤 확인 버튼을 누르면 데이터가 나옵니다.
```
![image](https://user-images.githubusercontent.com/85227582/162577806-26746ddb-f0e4-4f23-8192-7136ea78c05d.png)
![image](https://user-images.githubusercontent.com/85227582/162577812-64cf5697-7b49-4812-9dce-b5ab6b9fb763.png)
<br>
<br>
<br>
<br>
<br>
<br>
<br>

## :pushpin: 발주
```
* 발주파트에서는 재고관리와 발주를 할 수 있습니다.
```

---
### :point_down: 재고
```
* 재고들의 현황을 알 수 있고 재고를 수정 및 삭제할 수 있습니다.
* 결과는 재고목록에 업데이트 됩니다.
```
![image](https://user-images.githubusercontent.com/85227582/162578297-f9e38e0e-d108-4e4b-bd94-7b3ea965510f.png)
<br>
<br>
<br>

---
### :point_down: 발주
```
* 품목 목록 테이블 행을 클릭하면 발주 카트에 품목을 담을 수 있는 프레임이 뜹니다.
* 품목을 카트에 추가시키면 총 품목의 결제금액이 업데이트 됩니다.
* 발주버튼을 누르면 발주가 들어가 발주목록이 업데이트 됩니다.
```
![image](https://user-images.githubusercontent.com/85227582/162578876-a0167953-6b94-41a2-b07b-b63ae2f81393.png)
<br>
<br>
<br>

---
### :point_down: 발주 상세내역
```
* 발주목록 테이블에서 우클릭을 하게 되면 발주 상세내역을 확인할 수 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162579015-dcb64259-7209-4294-9cc0-4f01d5133a2b.png)
<br>
<br>
<br>
<br>
<br>
<br>
<br>

## :pushpin: 사원
```
* 사원파트는 매니저급만 들어갈 수 있기 때문에 로그인화면이 다시 나옵니다.
```

---
### :point_down: 사원 관리
```
* 재로그인에 성공하면 기본 사원 정보가 나옵니다.
* 사원 등록, 수정, 삭제를 통해 사원을 관리할 수 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162579171-c6e14148-5b90-4dfb-98bc-15d9fa5294ea.png)
<br>
<br>
<br>

---
### :point_down: 사원 관리
```
* 사원 등록, 수정은 다른 프레임을 통하여 정보를 넣을 수 있도록 하였습니다.
* 사원 수정의 경우는 테이블 행을 클릭하여 그 행의 정보를 읽어들여서 다른 프레임에 정보를 입력시켜주었습니다.
* 입력된 정보를 기반으로 수정이 가능하게 하였습니다.
* 삭제는 사원 정보 테이블을 클릭하여 삭제할 수 있도록 구현하였습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162579250-6b735ecd-1c93-4996-b9b0-ebf76c8314a4.png)
<br>
<br>
<br>
<br>
<br>
<br>
<br>

## :pushpin: 회원
```
* 회원 관리창 기본 화면입니다.
* 다른 페이지와 마찬가지로 로그인한 계정의 정보와 시간을 제공합니다.
* 상단 우측 버튼을 클릭하여 회원 등록, 수정, 삭제를 할 수 있습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162580688-ccba37f8-9db3-403a-aa0d-971afa6f31bd.png)
<br>
<br>
<br>

---
### :point_down: 회원 등록
```
* 멤버ID는 시퀀스를 사용하여 자동 생성되므로 입력하지 못하게 설정했습니다.
* 전화번호 또한 중복값이 발생하지 않기 때문에 중복 확인을 통해 기존에 이미 가입한 회원인지 확인할 수 있게 했습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162580774-d7062982-01bb-4a59-b95b-5a670aa84ca6.png)
<br>
<br>
<br>

---
### :point_down: 회원 수정
```
* 선택한 회원의 정보를 그대로 불러와 수정 가능하게 했습니다.
* 멤버ID는 PK이기 때문에 회원등록과 마찬가지로 수정이 불가능하게 했습니다.
```
![image](https://user-images.githubusercontent.com/85227582/162580876-b580e2ab-5ca5-44a4-a4fe-1c4d41cfd01d.png)
<br>
<br>
<br>

---
### :point_down: 회원 삭제
```
* 회원을 선택한 후 회원 삭제 버튼을 누르면 바로 데이터가 삭제됩니다.
```
![image](https://user-images.githubusercontent.com/85227582/162580939-0a8c4f59-1993-40f1-a0cf-280912642215.png)
