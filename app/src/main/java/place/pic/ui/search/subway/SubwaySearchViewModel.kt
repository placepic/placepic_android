package place.pic.ui.search.subway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

class SubwaySearchViewModel {

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
        allSubways.addAll(stubSubways())
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
        _selectedSubways.value = getCurrentSelectedSubways().filter { it.id != subway.id }
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
}