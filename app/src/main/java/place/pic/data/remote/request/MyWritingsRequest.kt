package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.MyWritingsResponse

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

class MyWritingsRequest : BaseRequest<MyWritingsResponse>() {

    fun send(token: String, groupIdx: Int) {
        PlacePicService.getInstance()
            .requestMyWritings(
                token = token,
                groupIdx = groupIdx
            ).enqueue(this)
    }
}