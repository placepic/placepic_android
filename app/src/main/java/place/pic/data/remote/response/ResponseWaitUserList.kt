package place.pic.data.remote.response

data class ResponseWaitUserList(
    val email: String,
    val gender: Int,
    val groupIdx: Int,
    val groupUserIdx: Int,
    val part: String,
    val password: String,
    val phoneNumber: String,
    val profileImageUrl: String,
    val profileThumbNailImageUrl: Any,
    val salt: String,
    val state: Int,
    val userBirth: String,
    val userCreatedAt: Int,
    val userIdx: Int,
    val userName: String
)