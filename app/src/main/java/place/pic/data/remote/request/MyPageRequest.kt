package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.MyPageResponse

data class MyPageRequest(
    val groupIdx: Int
)

class UserInfoRequest(
    val groupIdx: Int
) : BaseRequest<MyPageResponse>() {
    fun send(token: String) {
        PlacePicService.getInstance()
            .requestMyPage(
                token = token,
                groupIdx = groupIdx
            )
            .enqueue(this)
    }
}
