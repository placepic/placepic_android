package place.pic.data.remote.request

import android.text.TextUtils
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import place.pic.data.remote.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Malibin
 * on 7월 08, 2020
 */

open class BaseRequest<T> : Callback<BaseResponse<T>> {

    private var onSuccessListener: ((response: BaseResponse<T>) -> Unit)? = null
    private var onFailureListener: ((response: BaseResponse<Unit>) -> Unit)? = null

    override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        if (response.isSuccessful) {
            onSuccessListener?.invoke(response.body() ?: return)
            return
        }
        val errorBody = response.errorBody()?.string() ?: return
        val errorResponse = createFailureResponseBody(errorBody)
        onFailureListener?.invoke(errorResponse)
    }

    fun setOnSuccessListener(onSuccessListener: ((response: BaseResponse<T>) -> Unit)?) {
        this.onSuccessListener = onSuccessListener
    }

    fun setOnFailureListener(onFailureListener: ((response: BaseResponse<Unit>) -> Unit)?) {
        this.onFailureListener = onFailureListener
    }

    private fun createFailureResponseBody(errorBody: String): BaseResponse<Unit> {
        val gson = GsonBuilder().create()
        val responseType = object : TypeToken<BaseResponse<Unit>>() {}.type
        return gson.fromJson(errorBody, responseType)
    }

    override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
        printErrorMessage(t)
    }

    private fun printErrorMessage(t: Throwable) {
        Log.d("Malibin Debug", "${t.message} \n")
        Log.d("Malibin Debug", "${t.localizedMessage} \n")
        Log.d("Malibin Debug", TextUtils.join("\n", t.stackTrace))
    }
}