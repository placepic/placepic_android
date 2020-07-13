package place.pic.data.remote.response

/**
 * Created By Malibin
 * on 7월 08, 2020
 */

data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T
)

