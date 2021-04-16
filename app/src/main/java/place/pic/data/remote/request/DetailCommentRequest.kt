package place.pic.data.remote.request

import place.pic.data.remote.api.PlacePicDetailService
import place.pic.data.remote.response.CommentResponse

class DetailCommentRequest(
    private val placeIdx: Int,
    private val groupIdx:Int,
) : BaseRequest<List<CommentResponse>>() {
    fun send(token:String) {
        PlacePicDetailService.getInstance()
            .requestComments(
                token = token,
                placeIdx = placeIdx,
                groupIdx = groupIdx,
            )
            .enqueue(this)
    }
}
