package place.pic.ui.main.home.banner.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.entity.PlaceGridItem

/**
 * Created By Malibin
 * on 9월 24, 2020
 */

class BannerDetailViewModel {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _places = MutableLiveData<List<PlaceGridItem>>()
    val places: LiveData<List<PlaceGridItem>>
        get() = _places

    init {
        requestBannerDetail()
    }

    private fun requestBannerDetail() {
        _isLoading.value = true
        loadPlaces(
            listOf(
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름", "dd역")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
                PlaceGridItem("", 1, "네임", 123, listOf("이이이이름")),
            )
        )

        // 먼가 통신코드
    }

    private fun loadPlaces(items: List<PlaceGridItem>) {
        _places.value = items
        _isLoading.value = false
    }
}