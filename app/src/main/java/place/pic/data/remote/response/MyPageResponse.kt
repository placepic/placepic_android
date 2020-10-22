package place.pic.data.remote.response

data class MyPageResponse (
    val userName : String,
    val part : String,
    val userImage : String,
    val state : Int,
    val bookMarkCnt : Int,
    val postCount : Int
)