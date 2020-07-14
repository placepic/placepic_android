package place.pic.data.entity

import java.io.Serializable

/**
 * Created By kimdahyee
 * on 07월 15일, 2020
 */
 
data class UserList (
    val groupIdx: Int,
    val userIdx: Int,
    val userName: String,
    val profileImageUrl: String,
    val state: Int,
    val part: String,
    val postCount: Int
) : Serializable