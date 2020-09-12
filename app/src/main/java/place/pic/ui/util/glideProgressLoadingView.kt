package place.pic.ui.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import place.pic.R

fun Context.glideProgressLoadingView(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setColorSchemeColors(R.color.colorPrimary)
    circularProgressDrawable.start()

    return circularProgressDrawable
}