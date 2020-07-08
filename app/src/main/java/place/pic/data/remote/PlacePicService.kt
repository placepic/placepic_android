package place.pic.data.remote

import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.ResponseKeywordTag
import place.pic.data.remote.response.SubwayResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

interface PlacePicService {

    @GET("/tag/{categoryIdx}")
    fun requestKeywordTag(
        @Header("token") token: String,
        @Path("categoryIdx") categoryIdx: Int
    ): Call<ResponseKeywordTag>

    @GET("/subway")
    fun getAllSubways(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ): Call<BaseResponse<List<SubwayResponse>>>

    companion object {
        const val BASE_URL = "http://3.34.209.95:3000"

        @Volatile
        private var instance: PlacePicService? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: provideService(PlacePicService::class.java)
                .apply { instance = this }
        }
    }
}
