package place.pic.ui.upload

import android.content.Intent
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.R

/**
 * Created By Malibin
 * on 7월 07, 2020
 */

class UploadPlaceViewModel {

    private val _imageUris = MutableLiveData<List<ImageUri>>()
    val imageUris: LiveData<List<ImageUri>>
        get() = _imageUris

    private val _toastEvent = MutableLiveData<@StringRes Int>()
    val toastEvent: LiveData<Int>
        get() = _toastEvent

    init {
        _imageUris.value = emptyList()
    }

    fun deleteImageUri(imageUri: ImageUri) {
        val currentImages = getCurrentImages().toMutableList()
        currentImages.remove(imageUri)
        _imageUris.value = currentImages
    }

    fun handleImageUris(intent: Intent?) {
        val remainCount = getCurrentRemainImagesCount()
        val imagesToAdd = ImageUriExtractor.from(intent).run {
            if (this.size <= remainCount) return@run this
            _toastEvent.value = R.string.imageCountMaxIsTen
            this.subList(0, remainCount)
        }
        _imageUris.value = getCurrentImages() + imagesToAdd
    }

    private fun getCurrentImages() = _imageUris.value
        ?: throw IllegalStateException("imageUris cannot be null")

    private fun getCurrentImagesCount() = _imageUris.value?.size ?: 0

    private fun getCurrentRemainImagesCount() = MAX_IMAGE_COUNT - getCurrentImagesCount()

    companion object {
        private const val MAX_IMAGE_COUNT = 10
    }
}