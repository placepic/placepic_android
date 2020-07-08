package place.pic.data.remote.response

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

 data class ResponseKeywordTag (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data : KeywordTagData?
)

data class KeywordTagData (
    val tagInx : Int,
    val tagName : String,
    val tagIsBasic : Int,
    val categoryIdx : Int
)