package place.pic.ui.search.place

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_place_search.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Place
import place.pic.data.entity.PlaceSearch
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.PlaceSearchResponse
import place.pic.ui.upload.UploadPlaceActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created By kimdahyee
 * on 7월 10일, 2020
 */

class PlaceSearchActivity : AppCompatActivity() {

    lateinit var placeSearchAdapter: PlaceSearchAdapter
    val placeDatas: MutableList<PlaceSearchData> = mutableListOf()

    //view에 표시할 정보만 저장하는 리스트
    val placeSearchResult: MutableList<PlaceSearch> = mutableListOf()
    //서버로부터 받아오는 모든 정보를 저장하는 리스트

    private val placePicService = PlacePicService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_search)
        /* inflate가 setContentView 안에 있어, 가려져 있는거야
        fragment와 activity는 생명 주기가 달라
        fragment는 view가 가려지면 backStack에 들어가고 view가 사라져서 다시 사용하려면
        계속해서 inflate를 통해 view를 만들어줘야해 */

        //groupIdx 꺼내기
        val intent = intent
        val groupIdx = PlacepicAuthRepository.getInstance(this).groupId ?: return
        val categoryIdx = intent.getSerializableExtra("categoryIdx") as Place.Type

        initRcv()

        img_back.setOnClickListener {
            onBackPressed()
        }

        et_place_search_input.setOnEditorActionListener { textView, action, event ->
            onSearchClick(action, groupIdx)
        }

        //클릭리스너 등록
        placeSearchAdapter.setItemClickListener(object : PlaceSearchAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.d("check check", "${placeSearchResult[position].placeName} 선택")

                val clickedPlaceIntent =
                    Intent(this@PlaceSearchActivity, UploadPlaceActivity::class.java)

                clickedPlaceIntent.putExtra("categoryIdx", categoryIdx)
                clickedPlaceIntent.putExtra("placeName", placeSearchResult[position].placeName)
                clickedPlaceIntent.putExtra(
                    "placeAddress",
                    placeSearchResult[position].placeAddress
                )
                clickedPlaceIntent.putExtra(
                    "placeRoadAddress",
                    placeSearchResult[position].placeRoadAddress
                )
                clickedPlaceIntent.putExtra("placeMapX", placeSearchResult[position].placeMapX)
                clickedPlaceIntent.putExtra("placeMapY", placeSearchResult[position].placeMapY)
                clickedPlaceIntent.putExtra("link", placeSearchResult[position].link)
                clickedPlaceIntent.putExtra(
                    "mobileNaverMapLink",
                    placeSearchResult[position].mobileNaverMapLink
                )
                clickedPlaceIntent.putExtra("alreadyIn", placeSearchResult[position].alreadyIn)

                startActivity(clickedPlaceIntent)
            }
        })
    }

    private fun initRcv() { //initRcv(view : View)
        placeSearchAdapter = PlaceSearchAdapter()
        //placeSearchAdapter = PlaceSearchAdapter(view.context)
        //->context를 굳이 전달할 필요가 없으니까 view도 필요없어
        recyclerview_place_search.adapter = placeSearchAdapter
        //recyclerView의 어댑터를 instaAdapter로 지정
    }

    private fun onSearchClick(action: Int, groupIdx: Int): Boolean {
        var handled = false
        when (action) {
            EditorInfo.IME_ACTION_SEARCH -> {
                getPlaceSearchResultFromServer(groupIdx)
                handled = true
            }
        }
        return handled
    }

    private fun getPlaceSearchResultFromServer(groupIdx: Int) {

        val token = PlacepicAuthRepository.getInstance(this).userToken ?: return
        val groupIdx = PlacepicAuthRepository.getInstance(this).groupId ?: return

        placePicService.getInstance()
            .requestPlaceSearch(
                token = token,
                groupIdx = groupIdx,
                query = et_place_search_input.text.toString()
            ).enqueue(object : Callback<BaseResponse<PlaceSearchResponse>> {
                override fun onFailure(
                    call: Call<BaseResponse<PlaceSearchResponse>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<PlaceSearchResponse>>,
                    response: Response<BaseResponse<PlaceSearchResponse>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        Log.d("typeCheck", "통신성공")
                        constraintLayout_empty_placeSearch.visibility = View.GONE
                        if (response.body()!!.success) {
                            Log.d("typeCheck", "${response.body()!!.data.javaClass}")
                            placeDatas.clear()
                            placeSearchResult.clear()
                            for (i in response.body()!!.data.result.indices) {
                                var address: String =
                                    response.body()!!.data.result[i].placeRoadAddress

                                if (address.isEmpty()) { //도로명 주소가 없으면 (""이면)
                                    address = response.body()!!.data.result[i].placeAddress
                                }

                                placeDatas.apply {
                                    add(
                                        PlaceSearchData(
                                            placeName = response.body()!!.data.result[i].placeName,
                                            placeLocation = address,
                                            alreadyIn = response.body()!!.data.result[i].alreadyIn
                                        )
                                    )
                                }
                                Log.d("print check", placeDatas.toString())

                                placeSearchAdapter.datas = placeDatas
                                placeSearchAdapter.notifyDataSetChanged()

                                placeSearchResult.apply {
                                    add(
                                        PlaceSearch(
                                            placeName = response.body()!!.data.result[i].placeName,
                                            placeAddress = response.body()!!.data.result[i].placeAddress,
                                            placeRoadAddress = response.body()!!.data.result[i].placeAddress,
                                            placeMapX = response.body()!!.data.result[i].placeMapX,
                                            placeMapY = response.body()!!.data.result[i].placeMapY,
                                            link = response.body()!!.data.result[i].link,
                                            mobileNaverMapLink = response.body()!!.data.result[i].mobileNaverMapLink,
                                            alreadyIn = response.body()!!.data.result[i].alreadyIn
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            })
    }
}