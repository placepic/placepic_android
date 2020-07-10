package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.PlaceTypeDetailsResponse

/**
 * Created By Malibin
 * on 7월 10, 2020
 */

class PlaceTypeDetailsRequest : BaseRequest<List<PlaceTypeDetailsResponse>>() {

    fun send(token: String) {
        PlacePicService.getInstance()
            .getPlaceTypeDetails(token = token)
            .enqueue(this)
    }
}