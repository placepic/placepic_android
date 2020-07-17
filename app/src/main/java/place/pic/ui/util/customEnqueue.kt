package place.pic.ui.util

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val NETWORK = "placepicNetwork"

fun <ResponseType> Call<ResponseType>.customEnqueue(
    onFail: () -> Unit = {},
    onSuccess: (Response<ResponseType>) -> Unit,
    onError: (Response<ResponseType>) -> Unit = {}
)
{
    val networkLog = "Network"
    this.enqueue(object : Callback<ResponseType>{
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d(networkLog, t.message)
            Log.d(networkLog, call.request().toString())
            Log.d(NETWORK, "통신에 실패했습니다.")
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess(response)
                return
            }
            Log.d(NETWORK,"통신 에러")
            onError(response)
        }
    })
}