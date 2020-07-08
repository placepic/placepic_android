# 🐷 placepic_Android : Heroid 
<img style="border: 1px solid black !important; border-radius:20px;" src="https://avatars2.githubusercontent.com/u/67547341?s=200&v=4" width="200px" />
<br/>

**![placepic_bedge](https://img.shields.io/badge/placepic-Sprint1-%23ff7adc)**

<br/>

* SOPT 26th APPJAM - Team **placepic**
    
* 프로젝트 기간: 2020.06.28 ~ 2020.07.18

<br>


## 📍 placepic service

 <b>우리들끼리 공유하는 최애 장소, 플레이스픽 </b>

 플레이스픽은 신뢰있는 커뮤니티 기반의 장소 정보 공유 플랫폼입니다. 

 **Service key Feature**
  * Exploration - 쉽고, 빠르게 원하는 장소를 탐색

  * Discovery - 가보고 싶은 장소를 발견할 수 있어야함

  * Interaction - 상호작용, 소통할 수 있는 공간

<br/>

## ✍ Core value

### 🔝 App Jam Goal
* 우리가 쓸 수 있고, 쓰고 싶은 서비스를 만들어낼 것이다.

### 👥우리가 추구하는 가치
* 사용자 경험에 대한 집착 
* 자기주도성
* 린 스타트업 정신
* PRIDE✨

<br/>

## 🖥 Code convention
 
- **git branch**

>[이슈](https://github.com/placepic/placepic_android/issues) 별로 branch 구성 

- **git commit message role** 

```
[Feat] 기능 추가

[Fix] 버그 수정

[Refactor] 리팩토링

[Chore] 간단한 수정

[Delete] 기능 삭제

[Docs] 문서
```

<br/>

## 👪 Communication

### [PlacePic - Kanban board](https://github.com/orgs/placepic/projects/1)

### [Android - Kanban board](https://github.com/placepic/placepic_android/projects/1)

### Notion 

### SLACK

<br/>

## 🤝 Role
 
 ### 혁
 - 장소리스트  **SPRINT#1**
 - 지하철검색  **SPRINT#1**
 - 최애장소 업로드 View  **SPRINT#1**
 - 이미지 업로드 구현  **SPRINT#1**
 
  ### 진수
 - 로그인  **SPRINT#1**
 - 그룹신청  **SPRINT#1**
 - 관리자 페이지  **SPRINT#1**
 - 서버 작업 중 ...

 ### 다혜
 - 장소검색  **SPRINT#1**
 - 태그선택  **SPRINT#1**
 - Chip 구현  **SPRINT#1**
 - 서버 작업 중 ...

 ### 수정 
 - 회원가입  **SPRINT#1**
 - 최애장소 업로드 View Layout  **SPRINT#1**
 - 이미지 업로드 초기 구현  **SPRINT#1**
 - 서버 작업 중 ... 


<br/>

## 0. ConstraintLayout을 이용한 화면 개발.

모든 화면은 Constraint Layout으로 이루어져 있음.

아래와 같은 화면만 절대 크기를 지정하였는데,

```

- activity_end_group_join.xml

- activity_join_group.xml

- item_group_list.xml

- item_photo.xml

- item_get_image_button.xml

- item_image_to_upload.xml

- item_place.xml

- item_subway_line.xml

```

-> 

서버에 업로드될 사진의 크기가 일정하지 않으므로 

사용자에게 보여주는 사진은 절대 크기로 지정하였음.

이외 이유가 없는 부분은 모두 wrap_content로 변경함

```

- activity_join_group.xml

```

-> RecyclerView 사용 시 일부분만 보여주기 위해 height로 크기 정함 

```

- activity_upload_place.xml

- fragment_places.xml

- item_place.xml

- item_place_search.xml

- item_search_subway.xml

```

-> 디자인된 line을 넣기 위해 height=1dp 속성 지정  

<br>

match_constraint 사용 뷰

```

- 모든 뷰의 상단 바 

- activity_keword_tag.xml 

- activity_login.xml

- activity_place_search.xml

- activity_search_subway.xml

- activity_sign_up, activiy_sign_up_second.xml

- activity_upload_place.xml

- activivty_useful_tag.xml

- fragment_places.xml

- item_place.xml

```

<br/>

## 1. 프로젝트 사용 라이브러리

```groovy
    // View
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation group: 'de.hdodenhof', name: 'circleimageview', version: '3.1.0'
    implementation 'com.google.android.material:material:1.1.0'

    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    //material 디자인 라이브러리
    implementation "com.google.android.material:material:1.2.0-alpha05"
    //이미지 로딩 라이브러리 : glide
    implementation "com.github.bumptech.glide:glide:4.10.0"
    compileOnly 'com.google.android.wearable:wearable:2.5.0'
    kapt "com.github.bumptech.glide:compiler:4.10.0"
    //동그란 이미지 커스텀 뷰 라이브러리 : https://github.com/hdodenhof/CircleImageView
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    
    //retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'

    implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
    implementation 'com.google.code.gson:gson:2.8.6'
```

2020.07.08 현재 사용 라이브러리 정리

<br/>

## 2. 프로젝트 구조

프로젝트 구조는 아직 협의중. 패키지구조에 관해서 회의진행중이므로 기본적으로 작업 진행된 부분만 묶어서 현재 구조를 가지고있음.

추후 변경될 가능성이 매우 높음

- data.remote
  - response
- network
- ui
  - extands
  - group
  - login
  - main
    - place
  - signup
  - upload
  - tag
  - search
    - subway
    - place

크게 주제가 나누어진 부분은 패키지로 구분하여 각 부분에 들어가있으며, 

아직 패키지로 구분하기 애매한 것을은 패키지로 묶어두지 않았습니다.

2020.07.08 기준

<br/>

## 3. 핵심 기능 구현 방법 정리 & 구현 화면

-> 추후 업데이트 예정 

<br/>

## 💻 place pig's server developer 

### **🙋 [혁] https://github.com/nightmare73** 

### **🙋‍ [진수] https://github.com/jinsu4755**

### **🙋‍ [다혜] https://github.com/kimdahyee**

### **🙋‍ [수정] https://github.com/LeeSuJung-dodung**





