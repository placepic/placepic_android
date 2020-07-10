package place.pic.ui.main.place

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Place
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.data.remote.request.PlaceTypeDetailsRequest
import place.pic.data.remote.response.PlaceTypeDetailsResponse
import place.pic.data.tempToken

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlacesViewModel {

    var currentPlaceType = MutableLiveData<Place.Type>().apply { value = Place.Type.ALL }

    private val _placeTypeDetails = MutableLiveData<List<PlaceTypeDetails>>()
    val placeTypeDetails: LiveData<List<PlaceTypeDetails>>
        get() = _placeTypeDetails

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
            addOnErrorListener { _placeTypeDetails.value = emptyList() }
        }.send(tempToken)
    }

    private fun loadPlaceTypeDetailsList(response: List<PlaceTypeDetailsResponse>) {
        Log.d("Malibin Debug", response.toString())
        _placeTypeDetails.value = response.map { it.toPlaceTypeDetail() }
    }

    fun getCurrentPlaceTypeDetails(): PlaceTypeDetails {
        return getCurrentPlaceTypeDetailsList().find { it.placeType == currentPlaceType.value }
            ?: throw IllegalStateException("cannot exist place type of ${currentPlaceType.value}")
    }

    private fun getCurrentPlaceTypeDetailsList() = _placeTypeDetails.value
        ?: emptyList()
}