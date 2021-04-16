package place.pic.ui.main.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.request.CommentApplyRequest
import place.pic.data.remote.request.DetailCommentRequest
import place.pic.data.remote.request.UserInfoRequest
import place.pic.data.remote.response.CommentResponse
import place.pic.data.remote.response.MyPageResponse

class DetailViewModel(
    localRepository: PlacepicAuthRepository
) : ViewModel() {
    private val _comments = MutableLiveData(listOf<CommentResponse>())
    val comments: LiveData<List<CommentResponse>>
        get() = _comments

    private val _userImage = MutableLiveData<String>()
    val userImage: LiveData<String>
        get() = _userImage

    val commentInput = MutableLiveData("")

    private val _hasComment = MutableLiveData(false)
    val hasComment: LiveData<Boolean>
        get() = _hasComment

    private val userToken = localRepository.userToken ?: error("user token is null")
    private val groupIdx = localRepository.groupId ?: error("group id is null")

    fun validateComment() {
        _hasComment.value = !commentInput.value.isNullOrBlank()
        Log.d("Test", _hasComment.value.toString())
    }

    fun loadComments(placeIdx: Int) {
        DetailCommentRequest(placeIdx, groupIdx).apply {
            addOnSuccessListener {
                onSuccessLoadComments(it.data)
            }
        }.send(userToken)
    }

    private fun onSuccessLoadComments(comments: List<CommentResponse>) {
        _comments.value = comments
    }

    fun loadUserInfo() {
        UserInfoRequest(groupIdx).apply {
            addOnSuccessListener { onSuccessLoadUserInfo(it.data) }
        }.send(userToken)
    }

    private fun onSuccessLoadUserInfo(data: MyPageResponse) {
        _userImage.value = data.userImage
    }

    fun commentApplyClickEvent(placeIdx: Int, onScrollEvent: (() -> Unit)? = null) {
        CommentApplyRequest(
            placeIdx = placeIdx,
            groupIdx = groupIdx,
            comment = commentInput.value ?: ""
        ).apply {
            addOnSuccessListener {
                loadComments(placeIdx)
                onScrollEvent?.invoke()
            }
        }.send(userToken)
    }
}
