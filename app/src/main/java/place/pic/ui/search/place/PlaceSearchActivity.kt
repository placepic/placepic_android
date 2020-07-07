package place.pic.ui.search.place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_place_search.*
import place.pic.R

/**
 * Created By kimdahyee
 * on 7월 , 2020
 */

class PlaceSearchActivity : AppCompatActivity() {

    lateinit var placeSearchAdapter: PlaceSearchAdapter
    val placeDatas: MutableList<PlaceSearchData> = mutableListOf<PlaceSearchData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_search)
        //inflate가 setContentView 안에 있어, 가려져 있는거야
        //fragment와 activity는 생명 주기가 달라 
        //fragment는 view가 가려지면 backStack에 들어가고 view가 사라져서 다시 사용하려면
        //계속해서 inflate를 통해 view를 만들어줘야해

        initRcv()
        loadDatas()
        //xml 코드를 메모리에 올린다 올려야 읽을 수 있어
    }

    private fun initRcv() { //initRcv(view : View)
        placeSearchAdapter = PlaceSearchAdapter()
        //placeSearchAdapter = PlaceSearchAdapter(view.context)
        //->context를 굳이 전달할 필요가 없으니까 view도 필요없어

        recyclerview_place_search.adapter = placeSearchAdapter
        //recyclerView의 어댑터를 instaAdapter로 지정
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
        }

        placeSearchAdapter.datas = placeDatas
        placeSearchAdapter.notifyDataSetChanged()
    }
}