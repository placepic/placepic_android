package place.pic.ui.upload

import android.net.Uri
import java.util.*

/**
 * Created By Malibin
 * on 7월 07, 2020
 */

data class ImageUri(
    val uri: Uri,
    val id: String = UUID.randomUUID().toString()
) {
    companion object {
        val EMPTY = ImageUri(Uri.EMPTY, "empty")
    }
}