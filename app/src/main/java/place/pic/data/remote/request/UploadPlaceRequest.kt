package place.pic.data.remote.request

import android.content.Context
import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import place.pic.data.entity.KeywordTag
import place.pic.data.entity.Subway
import place.pic.data.entity.UsefulTag
import place.pic.data.remote.PlacePicService
import place.pic.ui.upload.ImageUri

/**
 * Created By Malibin
 * on 7월 14, 2020
 */

class UploadPlaceRequest(
    private val title: String,
    private val oldAddress: String? = null,
    private val roadAddress: String? = null,
    private val katecXPosition: Int,
    private val katecYPosition: Int,
    private val placeReview: String,
    private val categoryIdx: Int,
    private val groupIdx: Int,
    private val keywords: List<KeywordTag>,
    private val features: List<UsefulTag>,
    private val subways: List<Subway>,
    private val images: List<ImageUri>
) : BaseRequest<Unit>() {

    fun send(context: Context, token: String) {
        PlacePicService.getInstance().uploadPlace(
            token = token,
            params = createPartMap(),
            arrayParams = createQueryMap(),
            image = createImageMultiParts(context)
        ).enqueue(this)
    }

    private fun createPartMap(): HashMap<String, RequestBody> {
        val partMap = HashMap<String, RequestBody>()
        oldAddress?.let { partMap["address"] = it.toRequestBody(MEDIA_TYPE_TEXT) }
        roadAddress?.let { partMap["roadAddress"] = it.toRequestBody(MEDIA_TYPE_TEXT) }
        partMap["title"] = title.toRequestBody(MEDIA_TYPE_TEXT)
        partMap["mapx"] = katecXPosition.toString().toRequestBody(MEDIA_TYPE_TEXT)
        partMap["mapy"] = katecYPosition.toString().toRequestBody(MEDIA_TYPE_TEXT)
        partMap["placeReview"] = placeReview.toRequestBody(MEDIA_TYPE_TEXT)
        partMap["categoryIdx"] = categoryIdx.toString().toRequestBody(MEDIA_TYPE_TEXT)
        partMap["groupIdx"] = groupIdx.toString().toRequestBody(MEDIA_TYPE_TEXT)
        return partMap
    }

    private fun createQueryMap(): HashMap<String, List<Int>> {
        val queryMap = HashMap<String, List<Int>>()
        queryMap["tags"] = keywords.map { it.tagIdx }
        queryMap["infoTags"] = features.map { it.tagIdx }
        queryMap["subwayIdx"] = subways.map { it.id }
        return queryMap
    }

    private fun createImageMultiParts(context: Context): List<MultipartBody.Part> {
        return images.map { it.toMultipart(context) }
    }

    companion object {
        val MEDIA_TYPE_TEXT = "text/plain".toMediaType()
    }
}