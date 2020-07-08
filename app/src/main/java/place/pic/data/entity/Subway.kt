package place.pic.data.entity

import androidx.annotation.ColorRes
import place.pic.R
import java.io.Serializable

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

data class Subway(
    val name: String,
    val line: List<Line>
) : Serializable {

    enum class Line(
        private val number: Int,
        val value: String,
        @ColorRes val colorCode: Int
    ) : Serializable {
        LINE_1(1, "1", R.color.subway1),
        LINE_2(2, "2", R.color.subway2),
        LINE_3(3, "3", R.color.subway3),
        LINE_4(4, "4", R.color.subway4),
        LINE_5(5, "5", R.color.subway5),
        LINE_6(6, "6", R.color.subway6),
        LINE_7(7, "7", R.color.subway7),
        LINE_8(8, "8", R.color.subway8),
        LINE_9(9, "9", R.color.subway9),
        LINE_10(10, "인천1", R.color.subway10),
        LINE_11(11, "인천2", R.color.subway11),
        LINE_12(12, "분당", R.color.subway12),
        LINE_13(13, "신분당", R.color.subway13),
        LINE_14(14, "경의중앙", R.color.subway14),
        LINE_15(15, "경춘", R.color.subway15),
        LINE_16(16, "공항", R.color.subway16),
        LINE_17(17, "의정부", R.color.subway17),
        LINE_18(18, "수인", R.color.subway18),
        LINE_19(19, "에버라인", R.color.subway19),
        LINE_20(20, "경강", R.color.subway20),
        LINE_21(21, "우이신설", R.color.subway21),
        LINE_22(22, "서해", R.color.subway22),
        LINE_23(23, "김포골드", R.color.subway23),
        LINE_24(24, "자기부상", R.color.subway24);

        companion object {
            fun findByNumber(number: Int): Line {
                return values().first { it.number == number }
            }
        }
    }
}