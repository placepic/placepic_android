# placepic_Android : Heroid 🐷

## 0. ConstraintLayout을 이용한 화면 개발.

모든 화면은 Constraint Layout으로 이루어져 있음.

아래와 같은 화면만 절대 크기를 지정하였는데,

- activity_end_group_join.xml

- item_group_list.xml

- item_photo.xml

- item_place.xm

서버에 업로드될 사진의 크기가 일정하지 않으므로 

사용자에게 보여주는 사진은 절대 크기로 지정하였음.

이외 이유가 없는 부분은 모두 wrap_content로 변경함





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
```

2020.07.07 현재 사용 라이브러리 정리



## 2. 프로젝트 구조

프로젝트 구조는 아직 협의중. 패키지구조에 관해서 회의진행중이므로 기본적으로 작업 진행된 부분만 묶어서 현재 구조를 가지고있음.

추후 변경될 가능성이 매우 높음

- ui
  - extands
  - group
  - login
  - main
  - upload
  - search
    - subway

일단 크게 주제가 나누어진 부분은 패키지로 구분하여 각 부분에 들어가있으며. 아직 패키지로 구분하기 애매한 것을은 패키지로 묶어두지 않았습니다.



2020.07.07 기준



## 3. 핵심 기능 구현 방법 정리 & 구현 화면

