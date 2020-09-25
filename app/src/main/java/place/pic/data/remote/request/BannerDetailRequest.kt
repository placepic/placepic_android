package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BannerDetailResponse

/**
 * Created By Malibin
 * on 9월 25, 2020
 */

class BannerDetailRequest : BaseRequest<BannerDetailResponse>() {

    fun send(token: String, groupId: Int, bannerId: Int) {
        PlacePicService.getInstance()
            .requestBannerDetail(
                token = token,
                groupId = groupId,
                bannerId = bannerId,
            ).enqueue(this)
    }
}