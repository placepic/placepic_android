package place.pic.data.remote

import place.pic.data.remote.response.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface RequestInterface {
    @GET("/auth/checkemail")
    fun requestRegister(@Body body:RequestRegister)
}