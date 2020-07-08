package place.pic.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

fun <T> provideService(clazz: Class<T>): T = Retrofit.Builder()
    .baseUrl(PlacePicService.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(httpLoggingClient())
    .build()
    .create(clazz)

private fun httpLoggingClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}
