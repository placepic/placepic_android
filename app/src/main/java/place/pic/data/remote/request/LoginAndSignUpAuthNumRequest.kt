package place.pic.data.remote.request

data class LoginAndSignUpAuthNumRequest(
    val phoneNumber:String,
    val certificationNumber:String
)