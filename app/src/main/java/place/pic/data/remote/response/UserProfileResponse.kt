package place.pic.data.remote.response

import place.pic.data.entity.Banner
import place.pic.data.entity.Profile
import java.util.*

data class UserProfileResponse (
    val profileUserName : String,
    val profilePart : String,
    val profileUserImage : String,
    val profileState : Int,
    val profilePostCount : Int
){
    fun toProfile() = Profile(
        userName = profileUserName,
        part = profilePart,
        userImage = profileUserImage,
        state = profileState,
        postCount = profilePostCount
    )
}