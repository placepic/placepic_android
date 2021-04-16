package place.pic.data.remote.api

import place.pic.data.entity.Comment
import place.pic.data.remote.provideService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.CommentResponse
import retrofit2.Call
import retrofit2.http.*

interface PlacePicDetailService {

    @GET("/places/{placeIdx}/group/{groupIdx}/comment")
    fun requestComments(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int,
        @Path("groupIdx") groupIdx: Int
    ): Call<BaseResponse<List<CommentResponse>>>

    @POST("/places/{placeIdx}/group/{groupIdx}/comment")
    fun requestPostComment(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int,
        @Path("groupIdx") groupIdx: Int,
        @Body body: Comment
    ): Call<BaseResponse<Unit>>

    @DELETE("/places/{placeIdx}/comment/{commentIdx}")
    fun requestDeleteComment(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int,
        @Path("commentIdx") commentIdx: Int
    ): Call<BaseResponse<Unit>>

    companion object {
        @Volatile
        private var instance: PlacePicDetailService? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: provideService(PlacePicDetailService::class.java)
                .apply { instance = this }
        }
    }
}
