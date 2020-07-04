package place.pic.ui.main.place

import place.pic.ui.search.subway.Subway
import java.util.*

/**
 * Created By Malibin
 * on 7월 03, 2020
 */

data class Place(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val subways: List<Subway>,
    val keywordTags: List<String>,
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
}