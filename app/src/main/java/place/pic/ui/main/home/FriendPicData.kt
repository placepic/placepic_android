package place.pic.ui.main.home

import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Subway
import java.util.*

/**
 * Created By kimdahyee
 * on 09월 05일, 2020
 */

data class FriendPicData (
    val profileImageUrl: String,
    val userName: String,
    val part: String,
    val imageUrl: String,
    val name: String,
    val subways: List<Subway>,
    //val keywordTags: List<KeywordTag>,
    val uploadDate: Date
) {
    companion object {
        fun empty() = FriendPicData (
            profileImageUrl = "",
            userName = "김다혜",
            part = "27기 안드로이드파트",
            imageUrl = "",
            name ="진성한우곱창",
            subways = emptyList(),
            //keywordTags = emptyList(),
            uploadDate = Date()
        )
    }
}