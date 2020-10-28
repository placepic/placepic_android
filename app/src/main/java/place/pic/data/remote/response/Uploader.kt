package place.pic.data.remote.response

data class Uploader(
    val part: String,
    val postCount: Int,
    val profileImageUrl: Any,
    val userName: String,
    val deleteBtn:Boolean,
    val userIdx: Int
)