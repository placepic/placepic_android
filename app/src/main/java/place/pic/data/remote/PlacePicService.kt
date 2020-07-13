package place.pic.data.remote

import place.pic.data.remote.request.RequestLogin
import place.pic.data.remote.request.RequestRegister
import place.pic.data.remote.request.RequestRegisterSecond
import place.pic.data.remote.response.*
import retrofit2.Call
import place.pic.data.remote.response.ResponseGroupList
import place.pic.data.remote.response.BaseResponse
import retrofit2.http.*

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

interface PlacePicService {

    @GET("/tag/{categoryIdx}")
    fun requestKeywordTag(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("categoryIdx") categoryIdx: Int
    ): Call<BaseResponse<List<KeywordTagResponse>>>

    @GET("/tag/default/{categoryIdx}")
    fun requestUsefulTag(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("categoryIdx") categoryIdx: Int
    ): Call<BaseResponse<List<UsefulTagResponse>>>

    @GET("/search/place/{groupIdx}")
    fun requestPlaceSearch(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int,
        @Query("query") query: String
    ): Call<BaseResponse<PlaceSearchResponse>>

    @GET("/subway")
    fun getAllSubways(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ): Call<BaseResponse<List<SubwayResponse>>>

    @POST("/auth/checkemail")
    fun requestRegister(@Body body: RequestRegister): Call<BaseResponse<RequestRegister>>

    @POST("/auth/signup")
    fun requestRegisterSecond(@Body body: RequestRegisterSecond): Call<BaseResponse<Unit>>

    @POST("/auth/signin")
    fun requestLogin(@Body body: RequestLogin): Call<BaseResponse<LoginResponse>>

    @GET("/category/all")
    fun getPlaceTypeDetails(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ): Call<BaseResponse<List<PlaceTypeDetailsResponse>>>

    @GET("/places/group/{groupIdx}")
    fun getFilteredPlaces(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int,
        @QueryMap queries: Map<String, String?>
    ): Call<BaseResponse<List<PlaceResponse>>>

    //그룹 관련 서버 연결
    @GET("/auth/groups")
    fun requestMyGroupList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ):Call<BaseResponse<List<ResponseGroupList>>>

    @GET("/auth/groups")
    fun requestGroupList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Query("filter") filter:String
    ):Call<BaseResponse<List<ResponseGroupList>>>

    @GET("/auth/groups/apply")
    fun requestGroupApplyList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ):Call<BaseResponse<List<ResponseGroupList>>>

    @POST("/auth/groups/apply/{groupIdx}")
    fun requestSigninGroup(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx:Int,
        @Body body: RequestSigninGroup
    ):Call<BaseResponse<ResponseSingupGroup>>

    //Admin 관련 서버 연결
    @GET("/auth/groups/admin/{groupIdx}")
    fun requestWaitGroupList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx:Int
    ):Call<BaseResponse<List<ResponseWaitUserList>>>

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
