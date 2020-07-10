package place.pic.data

import android.content.Context
import android.content.SharedPreferences

class PlacepicAuthRepository private constructor() {
    companion object {
        @Volatile
        private var instance: PlacepicAuthRepository? = null

        private lateinit var sharedPreference: SharedPreferences

        private var PLACEPIC_AUTH = "placepic_auth"

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: PlacepicAuthRepository().also {
                instance = it
                sharedPreference = context.getSharedPreferences(PLACEPIC_AUTH,Context.MODE_PRIVATE)
            }
        }
    }
    
    private var editer = sharedPreference.edit()

    val userToken : String?
        get() = sharedPreference.getString(PLACEPIC_AUTH,"")

    val groupId : Int?
        get() = sharedPreference.getInt(PLACEPIC_AUTH,0)

    fun saveUserToken(userToken:String){
        editer.putString(PLACEPIC_AUTH,userToken).apply()
    }

    fun saveGroupId(groupId: Int) {
        editer.putInt(PLACEPIC_AUTH,groupId).apply()
    }

    fun removeUserToken(){
        editer.remove(PLACEPIC_AUTH)
    }

    fun removeGroupId(){
        editer.remove(PLACEPIC_AUTH)
    }
    
}