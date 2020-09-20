package place.pic.ui.main.mypage.mywritings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.PlaceGridItem
import place.pic.data.remote.request.MyWritingsRequest

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

class MyWritingsViewModel(
    private val placePicAuthRepository: PlacepicAuthRepository
) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _myWritings = MutableLiveData<List<PlaceGridItem>>()
    val myWritings: LiveData<List<PlaceGridItem>>
        get() = _myWritings

    fun removeMyWriting(placeId: Int) {
        val currentWritings = getCurrentMyWritings().toMutableList()
        _myWritings.value = currentWritings.filter { it.placeIdx != placeId }
    }

    fun requestMyWritings() {
        _isLoading.value = true
        loadMyWritings(
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
//        MyWritingsRequest().apply {
//            addOnSuccessListener { loadMyWritings(it.data.toPlaceGridItems()) }
//            addOnFailureListener { onRequestFail(it.toString()) }
//        }.send(getUserToken(), getGroupId())
    }

    private fun loadMyWritings(writings: List<PlaceGridItem>) {
        _myWritings.value = writings
        _isLoading.value = false
    }

    private fun onRequestFail(message: String) {
        Log.d("Malibin", message)
        _isLoading.value = false
    }

    private fun getGroupId() = placePicAuthRepository.groupId
        ?: throw IllegalStateException("groupId cannot be null")

    private fun getUserToken() = placePicAuthRepository.userToken
        ?: throw IllegalStateException("userToken cannot be null")

    private fun getCurrentMyWritings() = _myWritings.value ?: emptyList()
}