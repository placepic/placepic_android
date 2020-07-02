package place.pic.ui.search.subway

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
        val value: String
    ) : Serializable {
        LINE_1(1, "1호선"),
        LINE_2(2, "2호선"),
        LINE_3(3, "3호선"),
        LINE_4(4, "4호선"),
        LINE_5(5, "5호선"),
        LINE_6(6, "6호선"),
        LINE_7(7, "7호선"),
        LINE_8(8, "8호선"),
        LINE_9(9, "9호선"),
        LINE_10(10, "인천 1호선"),
        LINE_11(11, "인천 2호선"),
        LINE_12(12, "분당선"),
        LINE_13(13, "신분당"),
        LINE_14(14, "경의중앙선"),
        LINE_15(15, "경춘선"),
        LINE_16(16, "공항"),
        LINE_17(17, "의정부"),
        LINE_18(18, "수인선"),
        LINE_19(19, "에버라인"),
        LINE_20(20, "경강선"),
        LINE_21(21, "우이신설"),
        LINE_22(22, "서해선"),
        LINE_23(23, "김포골드");

        companion object {
            fun findByNumber(number: Int): Line {
                return values().first { it.number == number }
            }
        }
    }
}