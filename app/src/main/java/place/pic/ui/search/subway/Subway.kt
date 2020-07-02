package place.pic.ui.search.subway

import androidx.annotation.ColorRes
import place.pic.R
import java.io.Serializable

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

data class Subway(
    val id: Int,
    val name: String,
    val line: List<Line>
) : Serializable {

    enum class Line(
        private val number: Int,
        val value: String,
        @ColorRes val color: Int
    ) : Serializable {
        LINE_1(1, "1", R.color.black40),
        LINE_2(2, "2", R.color.black40),
        LINE_3(3, "3", R.color.black40),
        LINE_4(4, "4", R.color.black40),
        LINE_5(5, "5", R.color.black40),
        LINE_6(6, "6", R.color.black40),
        LINE_7(7, "7", R.color.black40),
        LINE_8(8, "8", R.color.black40),
        LINE_9(9, "9", R.color.black40),
        LINE_10(10, "인천 1호선", R.color.black40),
        LINE_11(11, "인천 2호선", R.color.black40),
        LINE_12(12, "분당", R.color.black40),
        LINE_13(13, "신분당", R.color.black40),
        LINE_14(14, "경의", R.color.black40),
        LINE_15(15, "경춘", R.color.black40),
        LINE_16(16, "공항", R.color.black40),
        LINE_17(17, "의정부", R.color.black40),
        LINE_18(18, "수인", R.color.black40),
        LINE_19(19, "에버라인", R.color.black40),
        LINE_20(20, "경강선", R.color.black40),
        LINE_21(21, "우이신설", R.color.black40),
        LINE_22(22, "서해선", R.color.black40),
        LINE_23(23, "김포골드", R.color.black40);

        companion object {
            fun findByNumber(number: Int): Line {
                return values().first { it.number == number }
            }
        }
    }
}