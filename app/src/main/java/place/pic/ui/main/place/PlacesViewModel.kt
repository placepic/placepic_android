package place.pic.ui.main.place

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Place
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.data.remote.request.PlaceTypeDetailsRequest
import place.pic.data.remote.response.PlaceTypeDetailsResponse

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesViewModel(
    private val placepicAuthRepository: PlacepicAuthRepository
) {
    var currentPagerPosition = 0
    val currentPlaceType = MutableLiveData<Place.Type>().apply { value = Place.Type.ALL }

    private val placeTypeDetails = mutableListOf<PlaceTypeDetails>()

    private val _selectedSubways = MutableLiveData<List<Subway>>()
    val selectedSubways: LiveData<List<Subway>>
        get() = _selectedSubways

    private val _selectedKeywords = MutableLiveData<List<KeywordTag>>()
    val selectedKeywords: LiveData<List<KeywordTag>>
        get() = _selectedKeywords

    private val _selectedFeatures = MutableLiveData<List<UsefulTag>>()
    val selectedFeatures: LiveData<List<UsefulTag>>
        get() = _selectedFeatures

    init {
        clearFiltering()
        requestRemotePlaceTypeDetails()
    }

    fun clearFiltering() {
        _selectedSubways.value = emptyList()
        _selectedKeywords.value = emptyList()
        _selectedFeatures.value = emptyList()
    }

    fun selectSubways(subways: List<Subway>) {
        _selectedSubways.value = subways
    }

    fun selectKeywords(keywords: List<KeywordTag>) {
        _selectedKeywords.value = keywords
    }

    fun selectFeatures(features: List<UsefulTag>) {
        _selectedFeatures.value = features
    }

    private fun requestRemotePlaceTypeDetails() {
        PlaceTypeDetailsRequest().apply {
            addOnSuccessListener { loadPlaceTypeDetailsList(it.data) }
            addOnFailureListener { Log.d("Malibin Debug", it.toString()) }
        }.send(getUserToken())
    }

    private fun loadPlaceTypeDetailsList(response: List<PlaceTypeDetailsResponse>) {
        placeTypeDetails.addAll(response.map { it.toPlaceTypeDetail() })
    }

    private fun getUserToken() = placepicAuthRepository.userToken
        ?: throw IllegalStateException("token cannot be null")

    fun getCurrentPlaceTypeDetails(): PlaceTypeDetails {
        return placeTypeDetails.find { it.placeType == currentPlaceType.value }
            ?: throw IllegalStateException("cannot exist place type of ${currentPlaceType.value}")
    }
}