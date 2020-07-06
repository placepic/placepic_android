package place.pic.ui.main.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.ui.search.subway.Subway

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesViewModel {

    private var currentPlaceType = Place.Type.ALL

    private val _placeTypeDetails = MutableLiveData<List<PlaceTypeDetails>>()
    val placeTypeDetails: LiveData<List<PlaceTypeDetails>>
        get() = _placeTypeDetails

    private val _selectedSubways = MutableLiveData<List<Subway>>()
    val selectedSubways: LiveData<List<Subway>>
        get() = _selectedSubways

    init {
        _selectedSubways.value = emptyList()
        loadPlaceTypeDetailsFromRemote()
    }

    fun setCurrentPlaceType(placeType: Place.Type) {
        currentPlaceType = placeType
    }

    fun selectSubways(subways: List<Subway>) {
        _selectedSubways.value = subways
    }

    fun clearFiltering() {
        _selectedSubways.value = emptyList()
    }

    private fun loadPlaceTypeDetailsFromRemote() {

    }

    fun getCurrentPlaceTypeDetails(): PlaceTypeDetails {
        return getCurrentPlaceTypeDetailsList().find { it.placeType == currentPlaceType }
            ?: throw IllegalStateException("cannot exist place type of $currentPlaceType")
    }

    private fun getCurrentPlaceTypeDetailsList() = _placeTypeDetails.value
        ?: emptyList()
}