package place.pic

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

interface RequestInterface {
    @GET("/tag/{categoryIdx}")
    fun requestKeywordTag(@Body body: RequestKeywordTag): Call<ResponseKeywordTag>
}
