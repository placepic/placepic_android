package place.pic.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

fun <T> provideService(clazz: Class<T>): T = Retrofit.Builder()
    .baseUrl(PlacePicService.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(clazz)
