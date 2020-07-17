package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BookmarksResponse

/**
 * Created By Malibin
 * on 7월 17, 2020
 */

class BookmarksRequest : BaseRequest<BookmarksResponse>() {

    fun send(token: String, groupIdx: Int) {
        PlacePicService.getInstance()
            .requestBookmarks(
                token = token,
                groupIdx = groupIdx
            ).enqueue(this)
    }
}