package place.pic.data.remote.response

/**
 * Created By Malibin
 * on 7월 09, 2020
 */

data class BookmarksResponse(
    val result: List<PlaceItemResponse>,
    val count: Int
) {
    fun toBookmarks() = result.map { it.toBookmark() }
}
