package place.pic.ui

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import place.pic.data.entity.Subway
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

@BindingAdapter("date")
fun bindingBirthday(textView: TextView, date: Date?) {
    if (date == null) return
    val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    textView.text = dateFormat.format(date)
}

@BindingAdapter("subways")
fun bindingSubways(textView: TextView, subways: List<Subway>?) {
    if (subways == null) return
    val subwaysText = TextUtils.join("/", subways.map { it.name })
    textView.text = subwaysText
}

@BindingAdapter("isSelected")
fun bindingIsSelected(view: View, isSelected: Boolean?) {
    view.isSelected = isSelected ?: false
}

@BindingAdapter("backgroundTint")
fun bindingBackgroundTint(view: View, @ColorRes color: Int?) {
    if (color == null) return
    view.background.setColorFilter(
        ContextCompat.getColor(view.context, color),
        android.graphics.PorterDuff.Mode.SRC_IN
    )
}

@BindingAdapter("imageUrl")
fun bindingImageUrl(imageView: ImageView, imageUrl: String?) {
    if (imageUrl == null) return
    Glide.with(imageView)
        .load(imageUrl)
        .into(imageView)
}
