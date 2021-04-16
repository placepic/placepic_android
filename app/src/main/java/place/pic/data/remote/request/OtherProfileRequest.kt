package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.OtherProfileResponse

class OtherProfileRequest: BaseRequest<OtherProfileResponse>() {
    fun send(token: String, groupIdx: Int, userIdx:Int) {
        PlacePicService.getInstance()
            .requestOtherProfile(
                token = token,
                groupIdx = groupIdx,
                userIdx = userIdx,
            ).enqueue(this)
    }
}
