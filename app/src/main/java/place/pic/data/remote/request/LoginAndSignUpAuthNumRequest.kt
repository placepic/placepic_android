package place.pic.data.remote.request

import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.LoginResponse

/*
class LoginAndSignUpAuthNumRequest(

    val phoneNumber: String,
    val certificationNumber: String
)*/

class LoginAndSignUpAuthNumRequest : BaseRequest<LoginResponse>() {
    fun sendData(phoneNumber: String, certificationNumber: String) {
        PlacePicService.getInstance()
            .requestLoginAndSignUpAuthNum(
                body = LoginAndSignUpAuthNumParams(phoneNumber,certificationNumber)
            )
            .enqueue(this)
    }

    data class LoginAndSignUpAuthNumParams(
        val phoneNumber: String,
        val certificationNumber: String,
    )
}