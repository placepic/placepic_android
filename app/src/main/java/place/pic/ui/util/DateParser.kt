package place.pic.ui.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateParser(placeCreatedAt: Long) {
    /*2020-04-23T15:10:26.000Z*/

    private val newDateTime:String
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
    private val placePicDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)

    init {
        val date = Date(placeCreatedAt * 1000L)
        val formattingDate = dateFormat.format(date)
        this.newDateTime = formattingDate
    }


    private fun parseStringToCalendar(newDateTimeString: String) : Calendar {
        val date = dateFormat.parse(newDateTimeString)
        return Calendar.getInstance().apply { time = date ?: Date() }
    }

    private fun convertToTalkDateString(cal: Calendar) : String
            = placePicDateFormat.format(cal.time)

    /* 시간차이 계산하여 return*/
    fun calculateDiffDate() : String {
        val parsedDate = parseStringToCalendar(newDateTime)
        Log.d("parsedDate","$parsedDate")
        val diff = System.currentTimeMillis() - parsedDate.timeInMillis

        val diffMin = diff / 1000 / 60
        val diffDay = diffMin / 60 / 24
        val diffHour = diffMin / 60

        return when {
            diffDay > 0 -> convertToTalkDateString(parsedDate)
            diffHour > 0 -> "${diffHour}시간 전"
            diffMin > 0 -> "${diffMin}분 전"
            else -> "방금"
        }
    }

}