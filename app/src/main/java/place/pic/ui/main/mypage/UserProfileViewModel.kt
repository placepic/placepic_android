package place.pic.ui.main.mypage

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.PlaceGridItem
import place.pic.data.entity.Profile
import place.pic.data.remote.request.OtherProfileRequest
import place.pic.data.remote.response.OtherProfileResponse
import place.pic.ui.util.loadImageFrom

class UserProfileViewModel(
    private val placepicAuthRepository: PlacepicAuthRepository
) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _userPlace = MutableLiveData<List<PlaceGridItem>>()
    val userPlace: LiveData<List<PlaceGridItem>>
        get() = _userPlace

    private val _profile = MutableLiveData<Profile>()
    val profile:LiveData<Profile>
        get() = _profile


    fun requestUserWritings(userIdx:Int){
        _isLoading.value=true
        OtherProfileRequest().apply {
            addOnSuccessListener { loadOtherWritings(it.data)}
            addOnFailureListener { _isLoading.value = false }
            addOnErrorListener { _isLoading.value = false }
        }.send(getUserToken(), getGroupId(), userIdx)
    }

    private fun loadOtherWritings(response:OtherProfileResponse) {
        _userPlace.value = response.getPlaceGridItems()
        _profile.value= response.UserInfo
        _isLoading.value = false
    }

    private fun getGroupId() : Int = placepicAuthRepository.groupId
        ?: throw IllegalStateException("groupId cannot be null")

    private fun getUserToken() : String = placepicAuthRepository.userToken
        ?: throw IllegalStateException("userToken cannot be null")

}