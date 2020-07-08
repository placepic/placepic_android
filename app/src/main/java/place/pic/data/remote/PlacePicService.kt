package place.pic.data.remote

import place.pic.RequestKeywordTag
import place.pic.ResponseKeywordTag
import place.pic.data.remote.response.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

interface PlacePicService {

    @GET("/tag/{categoryIdx}")
    fun requestKeywordTag(@Body body: RequestKeywordTag): Call<ResponseKeywordTag>

    @POST("/auth/checkemail")
    fun requestRegister(@Body body : RequestRegister): Call<BaseResponse<RequestRegister>>

    @POST("/auth/signup")
    fun requestRegisterSecond(@Body body : RequestRegisterSecond) : Call<BaseResponse<Unit>>

    @POST("/auth/signin")
    fun requestLogin(@Body body : RequestLogin): Call<BaseResponse<RequestLogin>>

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
