package place.pic.ui.upload

import android.content.Context
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.*

/**
 * Created By Malibin
 * on 7월 07, 2020
 */

data class ImageUri(
    val uri: Uri,
    val id: String = UUID.randomUUID().toString()
) {
    fun toMultipart(context: Context): MultipartBody.Part {
        val file = File(getRealPath(context))
        val requestBody = file.asRequestBody(IMAGE_FILE_EXTENSION.toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(REQUEST_IMAGE_KEY, file.name, requestBody)
    }

    private fun getRealPath(context: Context): String {
        return ""
    }

    companion object {
        private const val IMAGE_FILE_EXTENSION = "image/*"
        private const val REQUEST_IMAGE_KEY = "image"

        val EMPTY = ImageUri(Uri.EMPTY, "empty")
    }
}