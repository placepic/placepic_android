package place.pic.data

import android.app.Activity
import android.content.Context
import place.pic.ui.main.mypage.GroupChangeFragment


class PlacepicAuthRepository private constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(context.packageName,Activity.MODE_PRIVATE)

    private var editor = sharedPreferences.edit()

    val userToken : String?
        get() = sharedPreferences.getString(PLACEPIC_AUTH+"token","")

   val groupId : Int?
        get() = sharedPreferences.getInt(PLACEPIC_AUTH+"groupId",0)

    fun saveUserToken(token:String){
        editor.putString(PLACEPIC_AUTH+"token",token).apply()
    }

    fun saveGroupId(groupId: Int) {
        editor.putInt(PLACEPIC_AUTH+"groupId",groupId).apply()
    }

    fun removeUserToken(){
        editor.remove(PLACEPIC_AUTH+"token")
    }

    fun removeGroupId(){
        editor.remove(PLACEPIC_AUTH+"groupId")
    }

    companion object {
        private const val PLACEPIC_AUTH = "placepic_auth_"

        @Volatile
        private var instance: PlacepicAuthRepository? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: PlacepicAuthRepository(context).apply {
                instance = this
            }
        }
    }
    
}