package place.pic.data.remote.request

import place.pic.data.entity.Comment
import place.pic.data.remote.api.PlacePicDetailService

class CommentApplyRequest(
    val placeIdx: Int,
    val groupIdx: Int,
    val comment: String,
) : BaseRequest<Unit>() {
    fun send(token: String){
        PlacePicDetailService.getInstance()
            .requestPostComment(
                token = token,
                placeIdx = placeIdx,
                groupIdx = groupIdx,
                body = Comment(comment)
            )
            .enqueue(this)
    }
}