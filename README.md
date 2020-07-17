# 🐷 placepic_Android : Heroid 
<img src="https://github.com/LeeSuJung-dodung/SoptAndroidExampleAll/blob/master/plpic2.png?raw=true" width="1000px" height="300dp"/>

**![placepic_bedge](https://img.shields.io/badge/placepic-Sprint1-%23ff7adc)** **![android_bedge](https://img.shields.io/badge/android-v4.0-brightgreen)**

<br/>

* SOPT 26th APPJAM - Team **placepic**
  
* 프로젝트 기간: 2020.06.28 ~ 2020.07.18

<br/>


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

- **git commit message rule** 

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

### Notion, SLACK

<br/>

## 🤝 Role

 ### 혁
 ```
    - 장소리스트  
    - 지하철검색  
    - 최애장소 업로드 View  
    - 이미지 업로드 구현  
    - 인앱브라우저 구현
    - 저장 페이지
    - custom 확장 함수 사용
    - kotlin collection 확장 함수 사용 
 ```


 ### 진수

```
    - 로그인, 로그인 페이지 View Layout 
    - 내 그룹, 승인대기 페이지
    - 그룹 신청 페이지, 그룹 신청 완료
    - 관리자 페이지  
    - 디테일 페이지  
    - kotlin collection 확장 함수 사용 : Let, forEach
    - 싱글턴 SharedPreference 구현
    - custom enqueue 작성
```


 ### 다혜
```
    - 장소검색 페이지  
    - 태그선택 페이지
    - Chip 구현  
    - PlacePicService 구현 
    - 순위 페이지 
```


 ### 수정 
```
    - 로그인, 로그인 메인, 회원가입 페이지 
    - 최애장소 업로드 View Layout  
    - 이미지 업로드 초기 구현  
    - Bottom Navigation 커스텀  
    - 로그아웃 
    - 마이페이지
    - 스플래쉬 화면  
```

---

<br/>

## 📈 Work Flow

<img src="https://github.com/LeeSuJung-dodung/SoptAndroidExampleAll/blob/master/AEWGWEG.png?raw=true"  width="800px" height = "1100px" />

---

<br/>

##  📝 ConstraintLayout


- 절대 크기 지정

서버에 업로드될 사진의 크기가 일정하지 않으므로 사용자에게 보여주는 사진은 절대 크기로 지정하였습니다.

```
- activity_detail_view.xml
- activity_end_group_join.xml
- activity_join_group.xml
- item_group_list.xml
- item_photo.xml
- item_get_image_button.xml
- item_image_to_upload.xml
- item_place.xml
- item_subway_line.xml
- fragment_my_page.xml
- item_group_list.xml
- item_like_user_list.xml
- item_place.xml
```

뷰에서 특정 구역을 설정하기 위해 height 속성 지정

```
- item_member.xml
```

<br/>

- 모든 뷰의 상단 바 

상단바의 높이를 56dp로 일정하게 하기 위해 값을 줌 height에 값을 주었습니다.

```
- activity_upload_place.xml
- fragment_places.xml
- item_place.xml
- item_place_search.xml
- item_search_subway.xml
- activity_user_info.xml
- activity_web.xml
- fragment_admin_page.xml
- item_group_list.xml
```

<br/>

- 디자인된 line을 넣기 위해 height 속성 지정  

bottom navigation을 커스텀하여 가운데를 버튼으로 바꾸기 위해 임의로 width, height 값 지정하였습니다.

```
- activity_main.xml
```

<br/>

- match_constraint 사용 뷰

```
- activity_detail_view.xml
- activity_keword_tag.xml 
- activity_login.xml
- activity_place_search.xml
- activity_search_subway.xml
- activity_sign_up.xml
- activiy_sign_up_second.xml
- activity_upload_place.xml
- activivty_useful_tag.xml
- fragment_places.xml
- item_place.xml
- activity_upload_place.xml
- activity_useful_tag.xml
- acticity_user_info.xml
...
```

<br/>

- layout 사용

데이터 바인딩으로 인해  layout안에 ConstraintLayout을 사용함

```
- activity_detail_veiw.xml
- activity_search_subway.xml
- activity_upload_place.xml
- fragment_loading.xml
- fragment_place_items.xml
- fragment_places.xml
- item_get_image_button.xml
- item_image_to_upload.xml
- item_keyword_tag.xml
- item_place.xml
- item_place_filter.xml
- item_searched_subway.xml
- item_subway.xml
- item_subway_line.xml

```

이외에는 모두 Constraintlayout을 사용하였습니다.

---

<br/>

## 📌 Function

**kotlin collection의 확장 함수 사용** 

1. **map**

   [ImageUriExtractor.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/upload/ImageUriExtractor.kt)

   [KeywordChips.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/upload/KeywordChips.kt)

   [PlacesPagerAdapter.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/place/adapter/PlacesPagerAdapter.kt)

   [FeatureChips.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/upload/FeatureChips.kt)

   [PlacesRequest.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/request/PlacesRequest.kt)

   [UploadPlaceRequest.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/request/UploadPlaceRequest.kt)

   [PlaceItemResponse.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/response/PlaceItemResponse.kt)

   [PlaceTypeDetailsResponse.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/response/PlaceTypeDetailsResponse.kt)

   [SubwayRespons.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/response/SubwayResponse.kt)

   [BasicBindingAdapter.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/BasicBindingAdapter.kt)

   [PlacesViewModel.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/place/PlacesViewModel.kt)

   [PlacesAdapter.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/place/adapter/PlacesAdapter.kt)

2. **filter**

   [SubwaySearchViewModel.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/search/subway/SubwaySearchViewModel.kt)

3. **run**

   [PlacesRequest.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/request/PlacesRequest.kt)

   [PlaceItemViewModel.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/place/items/PlaceItemsViewModel.kt)

   [UploadPlaceViewModel.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/upload/UploadPlaceViewModel.kt)

4. **also**

   [PlacesAdapter.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/place/adapter/PlacesAdapter.kt)

5. **Let**

   [SignUpGroupActivity.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/group/SignUpGroupActivity.kt)

   [PlacesRequest.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/request/PlacesRequest.kt)

   [UploadPlaceRequest.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/request/UploadPlaceRequest.kt)

   [GroupListActivity.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/group/GroupListActivity.kt)

   [JoinGroupActivity.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/group/joingrouplist/JoinGroupActivity.kt)

   [WaitGroupActivity.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/group/waitgrouplist/WaitGroupActivity.kt)

   [WaitUserListActivity.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/mypage/admin/WaitUserListActivity.kt)

6. **forEach**

   [KeywordChips.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/upload/KeywordChips.kt)

   [FeatureChips.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/upload/FeatureChips.kt)

   [DetailViewActivity.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/detail/DetailViewActivity.kt)

7. **first**

   [PlacesPagerAdapter.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/place/adapter/PlacesPagerAdapter.kt)

   [Place.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/entity/Place.kt)

   [Subway.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/entity/Subway.kt)

8. **sortedBy**

   [PlacesPagerAdapter.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/ui/main/place/adapter/PlacesPagerAdapter.kt)

9. **getOrElse**

   [PlaceItemResponse.kt](https://github.com/placepic/placepic_android/blob/develop/app/src/main/java/place/pic/data/remote/response/PlaceItemResponse.kt)

<br/>

**custom 확장 함수 사용** 

- hasSingleImage()

이미지 피커에서 이미지를 가져올 때 1개를 가져올 때와 여러개를 가져올 때 접근해야 할 필드가 다릅니다. 

그렇기에 한장짜리 이미지를 가지고있는 intent인지, 여러장을 가지고있는 intent인지 판단을 위해 작성했습니다. 

내장 확장 함수 map을 사용하므로써 데이터 가공의 가독성을 높혔습니다.

*<ImageUriExtractor.kt>*

```kotlin
...

fun from(intent: Intent?): List<ImageUri> {
            if (intent == null) return emptyList()
            if (intent.hasSingleImage()) return getSingleImage(intent)
            return getMultipleImages(intent)
        }

        private fun Intent.hasSingleImage(): Boolean {
            return this.data != null
        }

... 
```

---

<br/>

## 🔧 Libraries Used

| Name                    | Gradle                                                       |
| :---------------------- | ------------------------------------------------------------ |
| View                    | androidx.constraintlayout:constraintlayout:1.1.3             |
|                         | androidx.legacy:legacy-support-v4:1.0.0                      |
|                         | androidx.recyclerview:recyclerview:1.1.0                     |
|                         | group: 'de.hdodenhof', name: 'circleimageview', version: '3.1.0' |
| LiveData                | androidx.lifecycle:lifecycle-livedata-ktx:2.2.0              |
| Material Design         | com.google.android.material:material:1.3.0-alpha01           |
| glide                   | com.github.bumptech.glide:glide:4.11.0                       |
|                         | com.github.bumptech.glide:compiler:4.10.0                    |
| Circle Imageview Custom | de.hdodenhof:circleimageview:3.1.0                           |
|                         | com.android.support:design:29.0.0                            |
| Circle Indicator        | me.relex:circleindicator:1.2.2                               |
| Toggle Button Custom    | me.rishabhkhanna:CustomToggle:1.0.0                          |
| Gson                    | com.google.code.gson:gson:2.8.6                              |
| retrofit                | com.squareup.retrofit2:retrofit:2.7.1                        |
|                         | com.squareup.retrofit2:converter-gson:2.7.1                  |
|                         | com.squareup.retrofit2:retrofit-mock:2.6.2                   |
|                         | com.squareup.okhttp3:logging-interceptor:4.2.1               |

---

<br/>

##  🏰 Project Structure

<img src="https://github.com/LeeSuJung-dodung/SoptAndroidExampleAll/blob/master/3333.png?raw=true" />

---

<br/>

## ✔️ Core function Iplementation 

<br/>

1.  **Image 추출 유틸 클래스**

Intent를 넣기만하면 Image VO의 List을 반환하는 유틸성 클래스를 작성했습니다. 

정적팩토리메서드 패턴을 이용하여 의존성과 접근성을 낮추었습니다.

*<ImageUriExtractor.kt>*

```kotlin
package place.pic.ui.upload

import android.content.Intent

/**
 * Created By Hyeok
 */

class ImageUriExtractor {

    companion object {
        fun from(intent: Intent?): List<ImageUri> {
            if (intent == null) return emptyList()
            if (intent.hasSingleImage()) return getSingleImage(intent)
            return getMultipleImages(intent)
        }
    
        private fun Intent.hasSingleImage(): Boolean {
            return this.data != null
        }
    
        private fun getSingleImage(intent: Intent): List<ImageUri> {
            val imageUri = intent.data
                ?: throw IllegalArgumentException("getSingleImage should not be called when multiple images loaded")
            return listOf(ImageUri(imageUri))
        }
    
        private fun getMultipleImages(intent: Intent): List<ImageUri> {
            val clipData = intent.clipData
                ?: throw IllegalArgumentException("getMultipleImages should not be called when single image loaded")
    
            return IntRange(0, clipData.itemCount - 1)
                .map { clipData.getItemAt(it).uri }
                .map { ImageUri(it) }
        }
    
    }
}
```

<br/>

2. **Chip**

여러 곳에서 Chip을 생성하여 사용하기 때문에 ChipFactory라는 유틸성 클래스를  생성해서 

칩이 필요한 순간에 언제든지 함수 호출을 통해 Chip을 생성할 수 있도록 구현하였습니다.

<*ChipFactory*>

```kotlin
package place.pic.ui.tag

import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import place.pic.R

/**
 * Created By kimdahyee
 */

class ChipFactory {

    //객체를 생성하지않고 함수 호출만으로도 칩을 생성할 수 있도록 유틸성 method 만들어보기
    //factory pattern
    //factory.keyword.chip
    //newInstance()
    companion object {
        fun newInstance(layoutInflater: LayoutInflater): Chip {
            return layoutInflater.inflate(R.layout.chip_tag, null, false) as Chip
        }
    
        fun createSmallChip(layoutInflater: LayoutInflater): Chip {
            return layoutInflater.inflate(R.layout.chip_tag_small, null, false) as Chip
        }
    
        fun createDetailChip(layoutInflater: LayoutInflater): Chip {
            return layoutInflater.inflate(R.layout.chip_detail_tag,null,false) as Chip
        }
    }

}
```

<KeywordTagActivity> 에서 사용

<br/>

3. **Singleton Shared Preference**

Shared Preference를 싱글턴으로 멀티쓰레드 환경을 고려하여 한번만 객체가 생성되도록 클래스를 작성하였습니다.

<*PlacepicAuthRepository.kt*>

```kotlin
package place.pic.data

import android.app.Activity
import android.content.Context

/**
 * Created By Jinsu
 */

class PlacepicAuthRepository private constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(context.packageName,Activity.MODE_PRIVATE)
    
    private var editor = sharedPreferences.edit()
    
    val userToken : String?
        get() = sharedPreferences.getString(PLACEPIC_AUTH+"token","")

   val groupId : Int?
        get() = sharedPreferences.getInt(PLACEPIC_AUTH+"groupId",0)

    fun saveUserToken(token:String){
        editor.putString(PLACEPIC_AUTH+"token",token).apply()
    }
    
    fun saveGroupId(groupId: Int) {
        editor.putInt(PLACEPIC_AUTH+"groupId",groupId).apply()
    }
    
    fun removeUserToken(){
        editor.remove(PLACEPIC_AUTH+"token")
    }
    
    fun removeGroupId(){
        editor.remove(PLACEPIC_AUTH+"groupId")
    }
    
    companion object {
        private const val PLACEPIC_AUTH = "placepic_auth_"
    
        @Volatile
        private var instance: PlacepicAuthRepository? = null
    
        @JvmStatic
        fun getInstance(context:Context) = instance ?: synchronized(this) {
            instance ?: PlacepicAuthRepository(context).apply {
                instance = this
            }
        }
    }
}
```

---

<br/>

## VIEW VIDIEO

<p align="center">
<img src="https://github.com/LeeSuJung-dodung/SoptAndroidExampleAll/blob/master/KakaoTalk_20200717_232940915_01.gif?raw=true" />
<img src= "https://github.com/LeeSuJung-dodung/SoptAndroidExampleAll/blob/master/KakaoTalk_20200717_232940915_02.gif?raw=true" />
</p>

---

<br/>

## 💻 place pig's android developer 

### **🙋 [혁] https://github.com/nightmare73** 

### **🙋‍ [진수] https://github.com/jinsu4755**

### **🙋‍ [다혜] https://github.com/kimdahyee**

### **🙋‍ [수정] https://github.com/LeeSuJung-dodung**
