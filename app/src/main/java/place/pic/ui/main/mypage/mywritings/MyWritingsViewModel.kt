package place.pic.ui.main.mypage.mywritings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.MyWriting

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

    private val _myWritings = MutableLiveData<List<MyWriting>>()
    val myWritings: LiveData<List<MyWriting>>
        get() = _myWritings

    private fun getGroupId() = placePicAuthRepository.groupId
        ?: throw IllegalStateException("groupId cannot be null")

    private fun getUserToken() = placePicAuthRepository.userToken
        ?: throw IllegalStateException("userToken cannot be null")
}