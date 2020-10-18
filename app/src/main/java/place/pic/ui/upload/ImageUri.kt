package place.pic.ui.upload

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
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
        val requestBody = file.asRequestBody(IMAGE_FILE_EXTENSION.toMediaType())
        return MultipartBody.Part.createFormData(REQUEST_IMAGE_KEY, file.name, requestBody)
    }

    private fun getRealPath(context: Context): String {
        var filePath = ""
        val wholeID = DocumentsContract.getDocumentId(uri)

        val id = wholeID.split(":").toTypedArray()[1]
        val column = arrayOf(MediaStore.Images.Media.DATA)

        val sel = MediaStore.Images.Media._ID + "=?"
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            column, sel, arrayOf(id), null
        ) ?: return ""

        val columnIndex: Int = cursor.getColumnIndex(column[0])
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return filePath
    }

    companion object {
        const val IMAGE_FILE_EXTENSION = "image/*"
        const val REQUEST_IMAGE_KEY = "image"

        val EMPTY = ImageUri(Uri.EMPTY, "empty")
    }

}