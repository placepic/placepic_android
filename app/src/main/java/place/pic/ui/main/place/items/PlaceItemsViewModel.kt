package place.pic.ui.main.place.items

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Place
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.data.remote.request.PlacesRequest
import place.pic.data.remote.response.PlaceResponse
import place.pic.data.tempGroupId
import place.pic.data.tempToken

/**
 * Created By Malibin
 * on 7월 09, 2020
 */

class PlaceItemsViewModel(
    private val placeType: Place.Type
) {
    private var isFilterPreviousApplied = false

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _placeItems = MutableLiveData<List<Place>>()
    val placeItems: LiveData<List<Place>>
        get() = _placeItems

    private val _totalItemCount = MutableLiveData<Int>()
    val totalItemCount: LiveData<Int>
        get() = _totalItemCount

    fun loadPlaceItems(
        keywordTags: List<KeywordTag>? = null,
        usefulTags: List<UsefulTag>? = null,
        subways: List<Subway>? = null
    ) {
        if (_placeItems.value == null
            || isFilterApplied(keywordTags, usefulTags, subways)
            || isFilterPreviousApplied
        ) {
            requestRemotePlaceItems(keywordTags, usefulTags, subways)
            isFilterPreviousApplied = isFilterApplied(keywordTags, usefulTags, subways)
        }
    }

    private fun requestRemotePlaceItems(
        keywordTags: List<KeywordTag>? = null,
        usefulTags: List<UsefulTag>? = null,
        subways: List<Subway>? = null
    ) {
        _isLoading.value = true
        PlacesRequest(
            groupIdx = tempGroupId,
            placeCategory = placeType.position.run { if (this == 0) null else this },
            keywordTags = keywordTags,
            usefulTags = usefulTags,
            subways = subways
        ).apply {
            addOnSuccessListener { loadRemotePlacesItems(it.data) }
            addOnFailureListener { Log.d("Malibin", it.toString()) }
        }.send(tempToken)
    }

    private fun loadRemotePlacesItems(response: PlaceResponse) {
        _totalItemCount.value = response.count
        _placeItems.value = response.toPlaces()
        _isLoading.value = false
    }

    private fun isFilterApplied(
        keywordTags: List<KeywordTag>? = null,
        usefulTags: List<UsefulTag>? = null,
        subways: List<Subway>? = null
    ): Boolean {
        return !keywordTags.isNullOrEmpty() or
                !usefulTags.isNullOrEmpty() or
                !subways.isNullOrEmpty()
    }
}