package place.pic.data.remote.request

import android.text.TextUtils
import place.pic.data.entity.UsefulTag
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Subway
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.PlaceResponse

/**
 * Created By Malibin
 * on 7월 09, 2020
 */

class PlacesRequest(
    private val groupIdx: Int,
    private val placeCategory: Int? = null,
    private val keywordTags: List<KeywordTag>? = null,
    private val usefulTags: List<UsefulTag>? = null,
    private val subways: List<Subway>? = null
) : BaseRequest<List<PlaceResponse>>() {

    fun send(token: String) {
        PlacePicService.getInstance()
            .getFilteredPlaces(
                token = token,
                groupIdx = groupIdx,
                queries = createQueryMap()
            )
            .enqueue(this)
    }

    private fun createQueryMap(): Map<String, String> {
        val queries = mutableMapOf<String, String>()
        placeCategory?.let { queries[QUERY_PLACE_CATEGORY] = it.toString() }
        createSubwaysQuery()?.let { queries[QUERY_SUBWAYS] = it }
        createKeywordsAndFeaturesQuery()?.let { queries[QUERY_KEYWORDS_AND_FEATURES] = it }
        return queries
    }

    private fun createKeywordsAndFeaturesQuery(): String? {
        val keywords = this.keywordTags?.map { it.tagName } ?: emptyList()
        val features = this.usefulTags?.map { it.tagName } ?: emptyList()
        return TextUtils.join(",", keywords + features).run {
            if (this.isBlank()) null
            else this
        }
    }

    private fun createSubwaysQuery(): String? {
        val subways = this.subways?.map { it.name } ?: emptyList()
        return TextUtils.join(",", subways).run {
            if (this.isBlank()) null
            else this
        }
    }

    companion object {
        private const val QUERY_PLACE_CATEGORY = "categoryIdx"
        private const val QUERY_KEYWORDS_AND_FEATURES = "tagIdx"
        private const val QUERY_SUBWAYS = "subwayIdx"
    }
}