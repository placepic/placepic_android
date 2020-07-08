package place.pic.data.remote

data class RequestRegisterSecond(
    val email : String,
    val password : String,
    val userName : String,
    val userBirth : String,
    val gender : Int
)
