package place.pic.data.remote.response

data class MyPageResponse (
    val userName : String,
    val part : Boolean,
    val userImage : String,
    val state : Int,
    val postCount : Int
)