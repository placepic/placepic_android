package place.pic.data.remote.response

import place.pic.data.entity.MyWriting

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

data class MyWritingsResponse(
    val UserPlace: List<MyWritingResponse>,
    val placeCount: Int
) {
    fun toMyWritings(): List<MyWriting> {
        return UserPlace.map { it.toMyWriting() }
    }
}

data class MyWritingResponse(
    val placeIdx: Int,
    val placeImageUrl: String,
    val placeName: String,
    val likeCnt: Int,
    val subwayName: String
) {
    fun toMyWriting() = MyWriting(
        imageUrl = placeImageUrl,
        placeIdx = placeIdx,
        placeName = placeName,
        likeCount = likeCnt,
        subwayName = subwayName
    )
}
