package place.pic.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.FriendPicResponse
import place.pic.ui.main.home.banner.BannerHomeAdapter
import place.pic.ui.main.home.banner.BannerHomeData
import place.pic.ui.main.home.banner.BannerListActivity
import place.pic.ui.main.home.friendpic.FriendPicAdapter
import place.pic.ui.main.home.friendpic.FriendPicData
import place.pic.ui.util.DateParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var bannerHomeAdapter: BannerHomeAdapter
    lateinit var friendPicAdapter: FriendPicAdapter
    lateinit var layoutManager: LinearLayoutManager

    private val bannerDatas = mutableListOf<BannerHomeData>()
    private val friendPicDatas = mutableListOf<FriendPicData>()

    private val placePicService = PlacePicService

    var page = 1
    var isLoading = false
    val limit = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        loadBannerDatas()
        loadFriendPicDatas()
        //호출을 통해 infinite scroll을 위한 준비 완료

        img_btn_banner_list.setOnClickListener {
            val intent = Intent(context, BannerListActivity::class.java)
            startActivity(intent)
        }

        // recycler view scroll listener
        rv_friendPic.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = friendPicAdapter.itemCount

                if (!isLoading) { // isLoading == false
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        page++
                        dataMore() // 이때 서버 연결 요청 하고 파라미터로 페이지 넘기기
                    }
                }

            }
        })
    }

    private fun init(view: View) {
        bannerHomeAdapter = BannerHomeAdapter()
        vp_banner.adapter = bannerHomeAdapter
        vp_banner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        layoutManager = LinearLayoutManager(view.context)
        rv_friendPic.layoutManager = layoutManager

        friendPicAdapter = FriendPicAdapter()
        rv_friendPic.adapter = friendPicAdapter
    }

    private fun dataMore() {
        isLoading = true
        //progressbar_fp.visibility = View.VISIBLE
        val start = (page - 1) * limit
        val end = (page) * limit

        for (i in start..end) {
            val pdate: DateParser = DateParser(1600665738)
            val dateResult: String = pdate.calculateDiffDate() //UNIX 타임 변환

            friendPicDatas.apply {
                add(
                    FriendPicData(
                        profileImageUrl = "https://img.hankyung.com/photo/201911/03.20725204.1.jpg",
                        userName = i.toString(),
                        part = "27기 안드로이드파트",
                        imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                        liker = 123,
                        name = "진성한우곱창",
                        subways = listOf<String>("합정역", "홍대입구역"),
                        tags = listOf<String>("최영훈픽", "태그야제발좀", "한번에성공좀"), //list 전체를 넣어
                        uploadDate = dateResult,
                        content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써야할까요오오오오오오오"
                    )
                )
            } // 여기에 서버 호출 코드 들어가
        }

        Handler().postDelayed({
            if(::friendPicAdapter.isInitialized) {
                friendPicAdapter.datas = friendPicDatas
                friendPicAdapter.notifyDataSetChanged()
            } else {
                rv_friendPic.adapter = friendPicAdapter
            }
            isLoading = false
            //progressbar_fp.visibility = View.GONE
        }, 5000)
    }

    private fun loadBannerDatas() {
        bannerDatas.apply {
            add(
                BannerHomeData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페 TOP 10",
                    description = "",
                    imageUrl = "https://images.homify.com/c_fill,f_auto,q_0,w_740/v1495806612/p/photo/image/2031170/%C3%A3_-_%C3%A3%C3%B5_%C3%8E_%C3%9B%C3%80%C3%BA%C3%81%C3%95%C3%81_%C3%BA_8.jpg",
                    count = "1 / 8"
                )
            )

            add(
                BannerHomeData(
                    badgeBg = "#F6CB5C",
                    badge = "PICK",
                    title = "제목 테스트",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://images.homify.com/c_fill,f_auto,q_0,w_740/v1495806612/p/photo/image/2031170/%C3%A3_-_%C3%A3%C3%B5_%C3%8E_%C3%9B%C3%80%C3%BA%C3%81%C3%95%C3%81_%C3%BA_8.jpg",
                    count = "2 / 8"
                )
            )

            add(
                BannerHomeData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "알고보니 리사이클러가 안되는중",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg",
                    count = "3 / 7"
                )
            )
        }
        bannerHomeAdapter.datas = bannerDatas
        bannerHomeAdapter.notifyDataSetChanged()
    }

    private fun loadFriendPicDatas() {

        val pdate: DateParser = DateParser(1600665738)
        val dateResult: String = pdate.calculateDiffDate() //UNIX 타임 변환

        friendPicDatas.apply {
            add(
                FriendPicData(
                    profileImageUrl = "https://img.hankyung.com/photo/201911/03.20725204.1.jpg",
                    userName = "111김다혜",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    liker = 123,
                    name = "진성한우곱창",
                    subways = listOf<String>("합정역", "홍대입구역"),
                    tags = listOf<String>("최영훈픽", "태그야제발좀", "한번에성공좀"), //list 전체를 넣어
                    uploadDate = dateResult,
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써야할까요오오오오오오오"
                )
            )
        }
        friendPicAdapter.addItems(friendPicDatas)
    }

    private fun getFriendPicListFromServer(groupIdx: Int, page: Int) {

        val token = PlacepicAuthRepository.getInstance(requireContext()).userToken ?: return
        val groupIdx = PlacepicAuthRepository.getInstance(requireContext()).groupId ?: return

        placePicService.getInstance()
            .requestFriendPic(
                token = token,
                groupIdx = groupIdx,
                page = page
            ).enqueue(object : Callback<BaseResponse<FriendPicResponse>>{
                override fun onFailure(
                    call: Call<BaseResponse<FriendPicResponse>>,
                    t: Throwable
                ) {
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: Call<BaseResponse<FriendPicResponse>>,
                    response: Response<BaseResponse<FriendPicResponse>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        Log.d("typeCheck", "통신성공")
                        if (response.body()!!.success) {
                            Log.d("typeCheck", "${response.body()!!.data.javaClass}")
                        }
                    }
                }
            })
    }
}
