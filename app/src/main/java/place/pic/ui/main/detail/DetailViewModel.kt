package place.pic.ui.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import place.pic.data.remote.response.CommentResponse

class DetailViewModel : ViewModel(){
    private val _comments = MutableLiveData(listOf<CommentResponse>())
    val comments: LiveData<List<CommentResponse>>
        get() = _comments
}
