package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.SubwayResponse

/**
 * Created By Malibin
 * on 7월 08, 2020
 */

class SubwaysRequest : BaseRequest<List<SubwayResponse>>() {

    fun send() {
        PlacePicService.getInstance()
            .getAllSubways(token = "")
            .enqueue(this)
    }
}














































