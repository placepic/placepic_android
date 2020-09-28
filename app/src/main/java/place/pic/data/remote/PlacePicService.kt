package place.pic.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import place.pic.data.remote.request.*
import place.pic.data.remote.response.*
import retrofit2.Call
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

    @GET("/places/home/page/{groupIdx}")
    fun requestFriendPic(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int,
        @Query("page") page: Int
    ): Call<BaseResponse<FriendPicResponse>>

    @GET("/auth/groups/userlist/{groupIdx}")
    fun requestUserList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int
    ): Call<BaseResponse<UserListResponse>>

    @GET("/subway")
    fun getAllSubways(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ): Call<BaseResponse<List<SubwayResponse>>>

    @POST("/auth/certificate")
    fun requestLoginAndSignUpPhoneNum(
        @Body body: LoginAndSignUpPhoneNumRequest
    ): Call<BaseResponse<LoginAndSignUpPhoneNumResponse>>

    @POST("/auth/sp3/signin")
    fun requestLoginAndSignUpAuthNum(
        @Body body:LoginAndSignUpAuthNumRequest
    ): Call<BaseResponse<LoginResponse>>


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
    ): Call<BaseResponse<PlaceResponse>>

    //그룹 관련 서버 연결
    @GET("/auth/groups")
    fun requestGroupList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ): Call<BaseResponse<List<ResponseGroupList>>>

    @POST("/auth/groups/apply")
    fun requestGroupSignUp(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Body body: GroupSignUpRequest
    ): Call<BaseResponse<Unit>>

    /*
    * 마이 페이지 서버 연결
    */
    @GET("/auth/myInfo/{groupIdx}")
    fun requestMyPage(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int
    ): Call<BaseResponse<MyPageResponse>>

    @GET("/auth/myInfo/places/{groupIdx}")
    fun requestMyWritings(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int
    ): Call<BaseResponse<MyWritingsResponse>>

    /*
    * 프로필편집 서버연결
    */

    @PUT("/auth/myInfo/edit/{groupIdx}")
    fun profileEditRequest(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int,
        @Body body: ProfileEditRequest
    ):Call<BaseResponse<Unit>>

    /*
    * 관리자 페이지 서버 연결
    */

    // 그룹 대기중인 유저 리스트 조회
    @GET("/auth/groups/admin/{groupIdx}")
    fun requestWaitGroupList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int
    ): Call<BaseResponse<List<ResponseWaitUser>>>

    // 그룹 대기 유저 승인.
    @PUT("/auth/groups/admin/edit/{groupIdx}")
    fun requestAcceptUser(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int,
        @Body body: RequestAcceptUser
    ): Call<BaseResponse<Unit>>

    //그룹 대기 유저 거절.
    @DELETE("/auth/groups/admin/delete/{groupIdx}/{userIdx}")
    fun requestRejectUser(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int,
        @Path("userIdx") userIdx: Int
    ): Call<BaseResponse<Unit>>

    /*디테일 페이지*/
    //디테일 페이지 불러오기
    @GET("/places/{placeIdx}")
    fun requestDetail(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int
    ): Call<BaseResponse<DetailResponse>>

    @Multipart
    @POST("/places")
    fun uploadPlace(
        @Header("token") token: String,
        @PartMap params: HashMap<String, RequestBody>,
        @PartMap arrayParams: HashMap<String, List<Int>>,
        @Part image: List<MultipartBody.Part>
    ): Call<BaseResponse<Unit>>
    // https://github.com/square/retrofit/issues/1805

    //좋아요
    @POST("/places/like")
    fun requestToLike(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Body body: RequestToPlaceIdx
    ): Call<BaseResponse<Unit>>

    //좋아요 취소
    @DELETE("/places/like/{placeIdx}")
    fun requestToDelLike(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int
    ): Call<BaseResponse<Unit>>

    @GET("/places/like/{placeIdx}")
    fun requestToLikeList(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int
    ): Call<BaseResponse<List<Like>>>

    @POST("/places/bookmark")
    fun requestToBookmark(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Body body: RequestToPlaceIdx
    ): Call<BaseResponse<Unit>>

    @DELETE("/places/bookmark/{placeIdx}")
    fun requestToDelBookmark(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int
    ): Call<BaseResponse<Unit>>

    //장소 삭제
    @DELETE("/places/{placeIdx}")
    fun requestToDeletePlace(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("placeIdx") placeIdx: Int
    ): Call<BaseResponse<Unit>>

    @GET("places/bookmark/group/{groupIdx}")
    fun requestBookmarks(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupIdx") groupIdx: Int
    ): Call<BaseResponse<BookmarksResponse>>

    @GET("/places/group/{groupId}/banner/{bannerId}")
    fun requestBannerDetail(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("groupId") groupId: Int,
        @Path("bannerId") bannerId: Int,
    ): Call<BaseResponse<BannerDetailResponse>>

    companion object {
        const val BASE_URL = "http://3.34.222.185:3000"

        @Volatile
        private var instance: PlacePicService? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: provideService(PlacePicService::class.java)
                .apply { instance = this }
        }
    }
}
