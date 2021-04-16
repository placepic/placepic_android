package place.pic.data.remote.request

import place.pic.data.remote.api.PlacePicDetailService

class DeleteCommentRequest(
    private val placeIdx: Int,
    private val commentIdx: Int,
) : BaseRequest<Unit>() {
    fun send(token: String) {
        PlacePicDetailService.getInstance()
            .requestDeleteComment(
                token = token,
                placeIdx = placeIdx,
                commentIdx = commentIdx
            ).enqueue(this)
    }
}
