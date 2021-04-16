package place.pic.ui.util

import java.text.SimpleDateFormat
import java.util.*

fun unixDateTimeParser(placeCreatedAt: Long): String {
    val date = Date(placeCreatedAt * 1000L)
    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    val formattingDate = simpleDateFormat.format(date)
    return formattingDate
}