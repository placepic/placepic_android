package place.pic.data.entity

import java.util.*

/**
 * Created By Malibin
 * on 7월 03, 2020
 */

data class Place(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val subways: List<Subway>,
    val keywordTags: List<KeywordTag>,
    val uploadDate: Date,
    val uploaderName: String,
    val uploaderProfileUrl: String
) {
    companion object {
        fun empty() = Place(
            id = -1,
            name = "",
            imageUrl = "",
            subways = emptyList(),
            keywordTags = emptyList(),
            uploadDate = Date(),
            uploaderName = "",
            uploaderProfileUrl = ""
        )
    }

    enum class Type(val position: Int, val value: String) {
        ALL(0, "전체"),
        RESTAURANT(1, "맛집"),
        ALCOHOL(2, "술집"),
        CAFE(3, "카페"),
        STUDY(4, "스터디"),
        ETC(5, "기타");

        companion object {
            fun findByName(name: String): Type = values().first { it.value == name }

            fun findByPosition(position: Int): Type = values().first { it.position == position }
        }
    }
}