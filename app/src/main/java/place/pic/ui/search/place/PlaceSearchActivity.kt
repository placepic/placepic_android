package place.pic.ui.search.place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_place_search.*
import place.pic.R
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.PlaceSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By kimdahyee
 * on 7월 , 2020
 */

class PlaceSearchActivity : AppCompatActivity() {

    lateinit var placeSearchAdapter: PlaceSearchAdapter
    val placeDatas: MutableList<PlaceSearchData> = mutableListOf()

    private val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjMsIm5hbWUiOiLstZzsmIHtm4giLCJpYXQiOjE1OTM2OTkxODMsImV4cCI6MTU5NjI5MTE4MywiaXNzIjoicGxhY2VwaWMifQ.rmFbeBfviyEzbMlMM4b3bMMiRcNDDbiX8bQtwL_cuN0"
    private val contentType: String = "application/json"

    private val placePicService = PlacePicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_search)
        //inflate가 setContentView 안에 있어, 가려져 있는거야
        //fragment와 activity는 생명 주기가 달라 
        //fragment는 view가 가려지면 backStack에 들어가고 view가 사라져서 다시 사용하려면
        //계속해서 inflate를 통해 view를 만들어줘야해

        //categoryIdx 꺼내기
        val intent = intent
        val groupIdx = intent.getIntExtra("groupIdx", 1)

        initRcv()
        //loadDatas()
        //xml 코드를 메모리에 올린다 올려야 읽을 수 있어

        et_place_search_input.setOnEditorActionListener { textView, action, event ->
            var handled = false
            when (action) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    getPlaceSearchResult(1)
                    handled = true
                }
            }
            handled
        }
    }

    private fun initRcv() { //initRcv(view : View)
        placeSearchAdapter = PlaceSearchAdapter()
        //placeSearchAdapter = PlaceSearchAdapter(view.context)
        //->context를 굳이 전달할 필요가 없으니까 view도 필요없어

        recyclerview_place_search.adapter = placeSearchAdapter
        //recyclerView의 어댑터를 instaAdapter로 지정
    }

    private fun getPlaceSearchResult(groupIdx: Int) {
        placePicService.getInstance()
            .requestPlaceSearch(
                contentType,
                token,
                groupIdx,
                et_place_search_input.text.toString()
            ).enqueue(object : Callback<BaseResponse<List<PlaceSearchResponse>>> {
                override fun onFailure(
                    call: Call<BaseResponse<List<PlaceSearchResponse>>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<PlaceSearchResponse>>>,
                    response: Response<BaseResponse<List<PlaceSearchResponse>>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        if (response.body()!!.success) {
                            for (i in response.body()!!.data.indices) {
                                placeDatas.apply {
                                    add(
                                        PlaceSearchData(
                                            placeName = response.body()!!.data[i].placeName,
                                            placeLocation = response.body()!!.data[i].placeRoadAddress
                                        )
                                    )
                                }
                                placeSearchAdapter.datas = placeDatas
                                placeSearchAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            })
    }

    private fun loadDatas() {
        placeDatas.apply {
            add(
                PlaceSearchData(
                    placeName = "소담한정식",
                    placeLocation = "서울특별시 서대문구 충정로 2가 69-12"
                )
            )
            add(
                PlaceSearchData(
                    placeName = "더테이블한정식",
                    placeLocation = "서울특별시 중구 태평로1가 25"
                )
            )
            add(
                PlaceSearchData(
                    placeName = "정식카페",
                    placeLocation = "서울특별시 노원구 392번지"
                )
            )
            add(
                PlaceSearchData(
                    placeName = "그녀의 한정식",
                    placeLocation = "서울특별시 마포구 도화동 251-8"
                )
            )
            add(
                PlaceSearchData(
                    placeName = "소담한정식",
                    placeLocation = "서울특별시 서대문구 충정로 2가 69-12"
                )
            )
        }

        placeSearchAdapter.datas = placeDatas
        placeSearchAdapter.notifyDataSetChanged()
    }
}