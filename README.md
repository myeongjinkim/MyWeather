# VideoBlur  
openweathermap api를 활용한 날씨 어플  

# 개발 환경

Android Gradle Plugin Version : 4.1.1

Gradle Version : 6.5

테스트 PC OS : Windows10 x64

테스트 스마트폰 : S20, G8  

targetSdkVersion : 30

개발 언어 : Kotlin  

디자인 패턴 : mvvm + repository  

사용 기술 : 
* Retrofit2 for rest api
* RxJava-rxkotlin
* Picasso
* Room
* Coroutine
* Hilt
* Data Binding
* BindingAdapter

# 개발 내용

* Retrofit2을 이용한 rest api 통신

* Rxkotlin을 사용하여 rest api 통신 비동기화 처리

* Picasso를 이용해 이미지 표시

* Room을 이용해 데이터 베이스 사용

* Coroutine을 사용하여 Room 비동기화 처리

* Data Binding, BindingAdapter을 사용해 mvvm + repository 구현

* Hilt를 사용하여 종속 항목 삽입

# 사용법

local.properties에 openweathermap에서 받은 개인 api 키를 작성  
ex) OpenWeatherKey = "..."

# 어플 화면 스트린샷

* 정보가 불러올 시, 로딩 화면  
<img src="https://user-images.githubusercontent.com/11714725/111074195-3c7c5f00-8525-11eb-98a1-f17b1de06f38.png" width="200"/>

* 데이터 베이스에 정보가 없을 시, 첫 시작 화면  
<img src="https://user-images.githubusercontent.com/11714725/111073196-28cef980-8521-11eb-97e0-207aacbba23f.png" width="200"/>

* 데이터 베이스에 정보가 있을 시, 시작 화면  
<img src="https://user-images.githubusercontent.com/11714725/111073199-2a002680-8521-11eb-8196-dbef6b7b5a1a.png" width="200"/>

* 주소를 검색  
<img src="https://user-images.githubusercontent.com/11714725/111073201-2a002680-8521-11eb-9c50-bf166584bf60.png" width="200"/>

* 자세한 도로명 주소도 검색 가능  
<img src="https://user-images.githubusercontent.com/11714725/111073202-2a98bd00-8521-11eb-94bd-2fd52c5c3ca2.png" width="200"/>

* 해당 주소의 위도, 경도로 openweathermap rest api로부터 날씨 정보를 받음  
현재 날씨와 3시간 단위 날씨 예보, 일일 예보를 보여줌  
<img src="https://user-images.githubusercontent.com/11714725/111073203-2a98bd00-8521-11eb-91ed-8c5d10170027.png" width="200"/>