package place.pic.data.entity

/**
 * Created By kimdahyee
 * on 09월 27일, 2020
 */
 
data class FriendPic (
    val userIdx: Int,
    val placeIdx: Int,
    val groupIdx: Int,
    val userName: String,
    val part: String,
    val profileImageUrl: String,
    val placeName: String,
    val placeReview: String,
    val placeImageUrl: String,
    val placeCreatedAt: Int,
    //uploadDate 서버한테 받는건 Int, 출력하는건 String
    val subway: List<String>,
    val tag: List<String>,
    val likeCnt: Int,
    val commentCnt: Int
)