package place.pic.ui.main.home.banner.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Banner
import place.pic.data.entity.PlaceGridItem
import place.pic.data.remote.request.BannerDetailRequest
import place.pic.data.remote.response.BannerDetailResponse

/**
 * Created By Malibin
 * on 9월 24, 2020
 */

class BannerDetailViewModel(
    private val placepicAuthRepository: PlacepicAuthRepository
) {
    private val bannerDetailRequest = BannerDetailRequest()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _banner = MutableLiveData<Banner>()
    val banner: LiveData<Banner>
        get() = _banner

    private val _places = MutableLiveData<List<PlaceGridItem>>()
    val places: LiveData<List<PlaceGridItem>>
        get() = _places

    fun requestBannerDetail(bannerId: Int) {
        _isLoading.value = true
        bannerDetailRequest.apply {
            addOnSuccessListener { loadBannerDetail(it.data) }
            addOnFailureListener { _isLoading.value = false }
            addOnErrorListener { _isLoading.value = false }
        }.send(getToken(), getGroupId(), bannerId)
    }

    private fun loadBannerDetail(response: BannerDetailResponse) {
        _places.value = response.getPlaceGridItems()
        _banner.value = response.getBanner()
        _isLoading.value = false
    }

    private fun getToken(): String = placepicAuthRepository.userToken
        ?: throw IllegalStateException("token cannot be null")

    private fun getGroupId(): Int = placepicAuthRepository.groupId
        ?: throw IllegalStateException("token cannot be null")
}
