package place.pic.ui.main.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.PlaceGridItem
import place.pic.data.remote.request.BookmarksRequest

/**
 * Created By Malibin
 * on 7월 17, 2020
 */

class BookmarksViewModel(
    private val placePicAuthRepository: PlacepicAuthRepository
) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _bookmarks = MutableLiveData<List<PlaceGridItem>>()
    val bookmarks: LiveData<List<PlaceGridItem>>
        get() = _bookmarks

    fun removeBookmark(placeId: Int) {
        val currentBookmarks = getCurrentBookmarks().toMutableList()
        val removedBookmarks = currentBookmarks.filter { it.placeIdx != placeId }
        _bookmarks.value = removedBookmarks
    }

    fun requestBookmarks() {
        _isLoading.value = true
        BookmarksRequest().apply {
            addOnSuccessListener { loadBookmarks(it.data.toPlaceGridItems()) }
            addOnFailureListener { onRequestFail(it.toString()) }
        }.send(getUserToken(), getGroupId())
    }

    private fun loadBookmarks(bookmarks: List<PlaceGridItem>) {
        _bookmarks.value = bookmarks
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

    private fun getCurrentBookmarks() = _bookmarks.value ?: emptyList()
}