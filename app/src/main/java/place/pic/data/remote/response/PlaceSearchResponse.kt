package place.pic.data.remote.response

import place.pic.data.entity.PlaceSearch

/**
 * Created By kimdahyee
 * on 07월 11일, 2020
 */
 
data class PlaceSearchResponse (
    val count: Number,
    val result: ArrayList<PlaceSearch>
)