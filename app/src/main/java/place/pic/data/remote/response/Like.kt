package place.pic.data.remote.response

data class Like(
    val likeCreatedAt: Int,
    val part: String,
    val profileImageUrl: String,
    val userName: String
)