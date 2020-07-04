package place.pic.ui

import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import place.pic.ui.search.subway.Subway
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