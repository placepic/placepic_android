package place.pic.ui.upload

import android.content.Intent

/**
 * Created By Malibin
 * on 7월 07, 2020
 */

class ImageUriExtractor {

    companion object {
        fun from(intent: Intent?): List<ImageUri> {
            if (intent == null) return emptyList()
            if (intent.hasSingleImage()) return getSingleImage(intent)
            return getMultipleImages(intent)
        }

        private fun Intent.hasSingleImage(): Boolean {
            return this.data != null
        }

        private fun getSingleImage(intent: Intent): List<ImageUri> {
            val imageUri = intent.data
                ?: throw IllegalArgumentException("getSingleImage should not be called when multiple images loaded")
            return listOf(ImageUri(imageUri))
        }

        private fun getMultipleImages(intent: Intent): List<ImageUri> {
            val clipData = intent.clipData
                ?: throw IllegalArgumentException("getMultipleImages should not be called when single image loaded")

            return IntRange(0, clipData.itemCount - 1)
                .map { clipData.getItemAt(it).uri }
                .map { ImageUri(it) }
        }
    }
}