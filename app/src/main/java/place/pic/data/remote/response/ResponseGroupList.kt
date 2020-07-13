package place.pic.data.remote.response

/**
 * Created By jinsu
 * on 7월 10, 2020
 */

data class ResponseGroupList(
    val postCount: Int,
    val userCount: Int,
    val groupIdx: Int,
    val groupImage: String,
    val groupName: String,
    val groupUserIdx: Int,
    val part: String,
    val phoneNumber: String,
    val state: Int,
    val userIdx: Int
)