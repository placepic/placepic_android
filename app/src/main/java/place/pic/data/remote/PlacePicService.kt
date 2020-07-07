package place.pic.data.remote

import place.pic.RequestKeywordTag
import place.pic.ResponseKeywordTag
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

interface PlacePicService {

    @GET("/tag/{categoryIdx}")
    fun requestKeywordTag(@Body body: RequestKeywordTag): Call<ResponseKeywordTag>

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
