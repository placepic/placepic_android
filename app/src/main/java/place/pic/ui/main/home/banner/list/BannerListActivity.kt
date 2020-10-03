package place.pic.ui.main.home.banner.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_banner_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BannerResponse
import place.pic.data.remote.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerListActivity : AppCompatActivity() {

    lateinit var bannerListAdapter: BannerListAdapter
    val bannerListDatas : MutableList<BannerListData> = mutableListOf()

    private val placePicService = PlacePicService
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjE4OCwicGhvbmVOdW1iZXIiOiIwMTA1NDA5OTg1OSIsImlhdCI6MTYwMDY2Mzk0NSwiZXhwIjoxNjA1ODQ3OTQ1LCJpc3MiOiJwbGFjZXBpYyJ9.ZlLonyyYdGye3JECXpkk_FHd3UonwS6QDl4sziDGB6g"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_list)

        init()
        getBannerListFromServer(17)

        img_banner_list_back.setOnClickListener { onBackPressed() }
    }

    private fun init() {
        bannerListAdapter =
            BannerListAdapter()
        rv_banner_list.adapter = bannerListAdapter
    }

    private fun getBannerListFromServer(groupIdx: Int) {

        val token = PlacepicAuthRepository.getInstance(this).userToken ?: return
        val groupIdx = groupIdx

        placePicService.getInstance()
            .requestBanner(
                token = token,
                groupIdx = groupIdx
            ).enqueue(object : Callback<BaseResponse<List<BannerResponse>>> {
                override fun onFailure(
                    call: Call<BaseResponse<List<BannerResponse>>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<List<BannerResponse>>>,
                    response: Response<BaseResponse<List<BannerResponse>>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        Log.d("typeCheck", "통신성공")
                        if (response.body()!!.success) {
                            Log.d("typeCheck", "${response.body()!!.data.javaClass}")
                            for (i in response.body()!!.data.indices) {
                                bannerListDatas.apply {
                                    add(
                                        BannerListData(
                                            badgeBg = response.body()!!.data[i].bannerBadgeColor,
                                            badge = response.body()!!.data[i].bannerBadgeName,
                                            title = response.body()!!.data[i].bannerTitle,
                                            description = response.body()!!.data[i].bannerDescription,
                                            imageUrl = response.body()!!.data[i].bannerImageUrl,
                                        )
                                    )
                                }
                            }
                            bannerListAdapter.datas = bannerListDatas
                            bannerListAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })
    }
}