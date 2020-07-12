package place.pic.data.remote.response

/**
 * Created By kimdahyee
 * on 07월 11일, 2020
 */
 
data class PlaceSearchResponse (
    val placeName: String,
    val placeAddress: String,
    val placeRoadAddress: String,
    val placeMapX: Number,
    val placeMapY: Number,
    val link: String,
    val mobileNaverMapLink: String,
    val alreadyIn: Boolean
)