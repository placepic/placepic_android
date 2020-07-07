package place.pic

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By kimdahyee
 * on 07월 07일, 2020
 */

//interface 구현체
//object: singleton, 아무데서나 접근 가능하고 객체는 단 하나만 생성 가능하다
object RetrofitService {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://3.34.209.95:3000")
        .addConverterFactory(GsonConverterFactory.create())
        //Json 데이터를 POJO class로 쉽게 변환해준다
        .build()

    var service : RequestInterface = retrofit.create(RequestInterface::class.java)
    //retrofit 객체 create 호출, interface 클래스 타입을 넘겨 실제 구현체를 만들어준다
}