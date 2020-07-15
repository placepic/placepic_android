package place.pic.data.entity

/**
 * Created By kimdahyee
 * on 07월 12일, 2020
 */

//서버로부터 받아오는 장소 검색 결과를 저장하기 위한 Data class
data class PlaceSearch (
    var placeName: String,
    var placeAddress: String,
    var placeRoadAddress: String,
    var placeMapX: Number,
    var placeMapY: Number,
    var link: String,
    var mobileNaverMapLink: String,
    var alreadyIn: Boolean
)