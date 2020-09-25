package place.pic.ui.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import place.pic.R

/**
 * Created By Malibin
 * on 9월 25, 2020
 */

fun ImageView.loadImageFrom(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.loadingimg_ic)
        .into(this)
}