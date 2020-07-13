package place.pic.data.remote.response

import place.pic.data.entity.UsefulTag
import place.pic.data.entity.KeywordTag

/**
 * Created By Malibin
 * on 7월 09, 2020
 */

data class TagResponse(
    val tagIdx: Int,
    val tagName: String,
    val tagIsBasic: Int
) {
    fun toKeywordTag() = KeywordTag(
        tagIdx = tagIdx,
        tagName = tagName
    )

    fun toUsefulTag() = UsefulTag(
        tagIdx = tagIdx,
        tagName = tagName
    )
}