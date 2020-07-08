package place.pic.data.remote.response

import place.pic.data.entity.Subway

/**
 * Created By Malibin
 * on 7월 08, 2020
 */

data class SubwayResponse(
    val subwayName: String,
    val subwayLine: List<Int>
) {
    fun toSubway() = Subway(
        id = 0,
        name = subwayName,
        line = subwayLine.map { Subway.Line.findByNumber(it) }
    )
}