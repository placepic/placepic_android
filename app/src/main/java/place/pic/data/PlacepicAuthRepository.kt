package place.pic.data

import android.app.Activity
import android.content.Context


class PlacepicAuthRepository private constructor(context: Context) {
    companion object {
        private const val PLACEPIC_AUTH = "placepic_auth"

        @Volatile
        private var instance: PlacepicAuthRepository? = null

        @JvmStatic
        fun getInstance(context:Context) = instance ?: synchronized(this) {
            instance ?: PlacepicAuthRepository(context).apply {
                instance = this
            }
        }
    }

    private val sharedPreferences = context.getSharedPreferences(context.packageName,Activity.MODE_PRIVATE)

    private var editor = sharedPreferences.edit()

    val userToken : String?
        get() = sharedPreferences.getString(PLACEPIC_AUTH,"")

   val groupId : Int?
        get() = sharedPreferences.getInt(PLACEPIC_AUTH,0)

    fun saveUserToken(userToken:String){
        editor.putString(PLACEPIC_AUTH,userToken).apply()
    }

    fun saveGroupId(groupId: Int) {
        editor.putInt(PLACEPIC_AUTH,groupId).apply()
    }

    fun removeUserToken(){
        editor.remove(PLACEPIC_AUTH)
    }

    fun removeGroupId(){
        editor.remove(PLACEPIC_AUTH)
    }
    
}