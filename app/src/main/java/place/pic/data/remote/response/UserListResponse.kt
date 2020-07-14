package place.pic.data.remote.response

import place.pic.data.entity.UserList

/**
 * Created By kimdahyee
 * on 07월 15일, 2020
 */
 
data class UserListResponse (
    val userCnt: Int,
    val userList: ArrayList<UserList>
)