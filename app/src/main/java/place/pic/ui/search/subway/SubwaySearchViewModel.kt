package place.pic.ui.search.subway

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Subway
import place.pic.data.remote.request.SubwaysRequest
import place.pic.data.remote.response.SubwayResponse
import place.pic.data.tempToken

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

class SubwaySearchViewModel(
    private val placepicAuthRepository: PlacepicAuthRepository
) {

    private val subwaysRequest = SubwaysRequest()

    private val allSubways = mutableListOf<Subway>()

    val searchQuery = MutableLiveData<String>()

    private val _searchedSubways = MutableLiveData<List<Subway>>()
    val searchedSubways: LiveData<List<Subway>>
        get() = _searchedSubways

    private val _selectedSubways = MutableLiveData<List<Subway>>()
    val selectedSubways: LiveData<List<Subway>>
        get() = _selectedSubways

    init {
        _searchedSubways.value = emptyList()
        _selectedSubways.value = emptyList()
        loadAllSubways()
    }

    private fun loadAllSubways() {
        subwaysRequest.apply {
            addOnSuccessListener { allSubways.addAll(getRemoteSubways(it.data)) }
            addOnFailureListener { Log.d("Malibin Debug", it.toString()) }
        }.send(getUserToken())
    }

    private fun getRemoteSubways(response: List<SubwayResponse>): List<Subway> {
        return response.map { it.toSubway() }
    }

    fun loadAlreadySelectedSubways(subways: List<Subway>) {
        _selectedSubways.value = subways
    }

    fun filterByName(name: String) {
        if (name.isBlank()) {
            _searchedSubways.value = emptyList()
            return
        }
        _searchedSubways.value = allSubways.filter { it.name.contains(name) }
    }

    fun removeSelectedSubways(subway: Subway) {
        _selectedSubways.value = getCurrentSelectedSubways().filter { it.name != subway.name }
    }

    fun addSelectedSubways(subway: Subway) {
        val currentSelectedSubways = getCurrentSelectedSubways()
        if (!currentSelectedSubways.contains(subway)) {
            _selectedSubways.value = currentSelectedSubways.toMutableList()
                .apply { add(0, subway) }
        }
    }

    fun contains(subway: Subway): Boolean {
        return getCurrentSelectedSubways().contains(subway)
    }

    fun getCurrentSelectedSubways() = _selectedSubways.value ?: emptyList()

    private fun getUserToken() = placepicAuthRepository.userToken
        ?: throw IllegalStateException("token cannot be null")
}