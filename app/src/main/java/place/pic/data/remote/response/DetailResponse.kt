package place.pic.data.remote.response

data class DetailResponse(
    val bookmarkCount: Int,
    val categoryIdx: Int,
    val placeRoadAddress: String,
    val imageUrl: List<String>,
    val isBookmarked: Boolean,
    val isLiked: Boolean,
    val keyword: List<String>,
    val likeCount: Int,
    val likeList: List<Like>,
    val placeCreatedAt: Int,
    val placeInfo: List<String>,
    val placeName: String,
    val placeReview: String,
    val subway: List<String>,
    val uploader: Uploader,
    val mobileNaverMapLink: String,
    val placeMapX: Double,
    val placeMapY: Double
)
