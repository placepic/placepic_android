package place.pic.data.remote.response

import place.pic.data.entity.FriendPic

/**
 * Created By kimdahyee
 * on 09월 27일, 2020
 */
 
data class FriendPicResponse (
    val totalPage: Int,
    val places: ArrayList<FriendPic>
)