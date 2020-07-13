package place.pic.ui.upload

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Place
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.ui.main.place.PlacesFragment

/**
 * Created By Malibin
 * on 7월 07, 2020
 */

class UploadPlaceViewModel(
    private val placePicAuthRepository: PlacepicAuthRepository
) {
    val placeReview = MutableLiveData<String>()
    private var katechXPosition: Int? = null
    private var katechYPosition: Int? = null
    private lateinit var placeTitle: String
    private lateinit var placeOldAddress: String
    private lateinit var placeRoadAddress: String
    private lateinit var placeType: Place.Type

    private val _imageUris = MutableLiveData<List<ImageUri>>()
    val imageUris: LiveData<List<ImageUri>>
        get() = _imageUris

    private val _subways = MutableLiveData<List<Subway>>()
    val subways: LiveData<List<Subway>>
        get() = _subways

    private val _keywords = MutableLiveData<List<KeywordTag>>()
    val keywords: LiveData<List<KeywordTag>>
        get() = _keywords

    private val _features = MutableLiveData<List<UsefulTag>>()
    val features: LiveData<List<UsefulTag>>
        get() = _features

    private val _toastEvent = MutableLiveData<@StringRes Int>()
    val toastEvent: LiveData<Int>
        get() = _toastEvent

    init {
        _imageUris.value = emptyList()
        _subways.value = emptyList()
        _keywords.value = emptyList()
        _features.value = emptyList()
    }

    fun uploadPlace(context: Context) {

    }

    // 생명주기로 GC한테 컬렉팅됬을때는 생각하지말자 시간없으니... ^^^^^^;;;;;;
    fun handlePreviousActivityRequestParams(intent: Intent?) {
        if (intent == null) throw IllegalArgumentException("previous request params must be sended")
        katechXPosition = intent.getIntExtra("placeMapX", -1)
        katechYPosition = intent.getIntExtra("placeMapY", -1)
        placeTitle = intent.getStringExtra("placeName") ?: ""
        placeOldAddress = intent.getStringExtra("placeAddress") ?: ""
        placeRoadAddress = intent.getStringExtra("placeRoadAddress") ?: ""
        placeType = Place.Type.findByPosition(intent.getIntExtra("categoryIdx", -1))
    }

    fun handleSubwaysIntent(intent: Intent?) {
        if (intent == null) return
        val subways = intent.getSerializableExtra(PlacesFragment.SUBWAYS_KEY) ?: return
        _subways.value = subways as List<Subway>
    }

    fun handleKeywordsIntent(intent: Intent?) {
        if (intent == null) return
        val keywords = intent.getSerializableExtra("checkedChip") ?: return
        _keywords.value = keywords as List<KeywordTag>
    }

    fun handleFeaturesIntent(intent: Intent?) {
        if (intent == null) return
        val features = intent.getSerializableExtra("checkedChip") ?: return
        _features.value = features as List<UsefulTag>
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