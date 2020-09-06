package place.pic.ui.main.home

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
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
    val liker: Int,
    val name: String,
    val subways: List<String>,
    val tags: ChipGroup,
    val uploadDate: Date,
    val content: String
) /*{
    companion object {

        val subways = listOf<String>("합정역", "홍대입구역")
        val tags = listOf<String>("혼술하기좋은", "단체석있는", "내부화장실", "24시간")
        //tag text 리스트

        fun empty(): FriendPicData {
            return FriendPicData (
                profileImageUrl = "",
                userName = "김다혜",
                part = "27기 안드로이드파트",
                imageUrl = "",
                liker = 101,
                name = "진성한우곱창",
                subways = subways,
                tags = chipGroup,
                uploadDate = Date(),
                content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써.."
            )
        }
    }
}*/