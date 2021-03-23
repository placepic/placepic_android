package place.pic.data.remote.response

import place.pic.data.entity.Banner
import place.pic.data.entity.Profile
import java.util.*

data class MyPageResponse (
    val userName : String,
    val part : String,
    val userImage : String,
    val state : Int,
    val bookMarkCnt : Int?,
    val postCount : Int,
    val groupName : String
)