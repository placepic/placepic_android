package place.pic.data.entity

/**
 * Created By kimdahyee
 * on 07월 12일, 2020
 */
 
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