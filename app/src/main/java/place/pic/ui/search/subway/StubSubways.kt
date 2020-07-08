package place.pic.ui.search.subway

import place.pic.data.entity.Subway

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

fun stubSubways(): List<Subway> {
    return listOf(
        Subway(
            0,
            "범계역",
            listOf(Subway.Line.LINE_4)
        ),
        Subway(
            1,
            "사당역",
            listOf(
                Subway.Line.LINE_4,
                Subway.Line.LINE_2
            )
        ),
        Subway(
            2,
            "공덕역",
            listOf(
                Subway.Line.LINE_5,
                Subway.Line.LINE_6,
                Subway.Line.LINE_16,
                Subway.Line.LINE_14
            )
        )
    )
}