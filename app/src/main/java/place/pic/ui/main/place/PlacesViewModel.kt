package place.pic.ui.main.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.ui.search.subway.Subway

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesViewModel {

    private val _selectedSubways = MutableLiveData<List<Subway>>()
    val selectedSubways: LiveData<List<Subway>>
        get() = _selectedSubways

    init {
        _selectedSubways.value = emptyList()
    }

    fun selectSubways(subways: List<Subway>) {
        _selectedSubways.value = subways
    }

    fun clearFiltering() {
        _selectedSubways.value = emptyList()
    }

}