package place.pic

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

interface RequestInterface {
    @GET("/tag/{categoryIdx}") //param
    fun requestKeywordTag(@Body body: RequestKeywordTag): Call<ResponseKeywordTag>
}


// 127.0.0.1:3000/search/groudIdx/1?tagIdx=1,2,3 //이게 query

