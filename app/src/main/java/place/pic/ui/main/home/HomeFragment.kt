package place.pic.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BannerResponse
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.FriendPicResponse
import place.pic.ui.main.home.banner.BannerHomeAdapter
import place.pic.ui.main.home.banner.BannerHomeData
import place.pic.ui.main.home.banner.detail.BannerDetailActivity
import place.pic.ui.main.home.banner.list.BannerListActivity
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

    var page = 1
    var isLoading = false
    var totalPage: Int = 0

    private val bannerHomeDatas = mutableListOf<BannerHomeData>()
    private val friendPicList = mutableListOf<FriendPicData>()

    private val placePicService = PlacePicService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        val token = PlacepicAuthRepository.getInstance(requireContext()).userToken ?: return
        val groupIdx = PlacepicAuthRepository.getInstance(requireContext()).groupId ?: return

        getBannerListFromServer(token, groupIdx)
        // initial items
        getFriendPicListFromServer(token, groupIdx, 1)

        img_banner_next_home.setOnClickListener {
            val intent = Intent(context, BannerListActivity::class.java)
            startActivity(intent)
        }

        //banner clickevent listener
        bannerHomeAdapter.setItemClickListener(object : BannerHomeAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val clickedBannerIntent =
                    Intent(context, BannerDetailActivity::class.java)
                clickedBannerIntent.putExtra("bannerId", bannerHomeDatas[position].bannerIdx)
                startActivity(clickedBannerIntent)
            }
        })

        layoutManager = LinearLayoutManager(context)
        rv_friendpic_home.layoutManager = layoutManager

        nestedScrollView_home.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(v.getChildAt(v.getChildCount() - 1) != null) {
                if ((scrollY >= (v.getChildAt(v.getChildCount() - 1)
                        .getMeasuredHeight() - v.getMeasuredHeight())) && scrollY > oldScrollY) {
                    progressbar_fp.visibility = View.VISIBLE
                    val handler = Handler()
                    handler.postDelayed({
                        page += 1
                        isLoading = true
                        friendPicList.clear()
                        getFriendPicListFromServer(token, 17, page)
                    }, 2000)
                }
            }
        })

        /* infinite scroll */
        /*rv_friendpic_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = friendPicAdapter.itemCount

                if (!isLoading && page < totalPage) { //isLoading == false
                    if (pastVisibleItem >= total - 1) {
                        //progressbar_fp.visibility = View.VISIBLE
                        val handler = Handler()
                        handler.postDelayed({
                            page += 1
                            isLoading = true
                            friendPicList.clear()
                            getFriendPicListFromServer(17, page)
                            //progressbar_fp.visibility = View.GONE
                        }, 2000)
                    }
                }
            }
        })*/
    }

    private fun init() {
        bannerHomeAdapter = BannerHomeAdapter()
        vp_banner_home.adapter = bannerHomeAdapter
        vp_banner_home.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        friendPicAdapter = FriendPicAdapter()
        rv_friendpic_home.adapter = friendPicAdapter
    }

    private fun getBannerListFromServer(token: String, groupIdx: Int) {

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
                                bannerHomeDatas.apply {
                                    add(
                                        BannerHomeData(
                                            bannerIdx = response.body()!!.data[i].bannerIdx,
                                            badgeBg = response.body()!!.data[i].bannerBadgeColor,
                                            badge = response.body()!!.data[i].bannerBadgeName,
                                            title = response.body()!!.data[i].bannerTitle,
                                            description = response.body()!!.data[i].bannerDescription,
                                            imageUrl = response.body()!!.data[i].bannerImageUrl,
                                            count = ""
                                        )
                                    )
                                }
                            }
                            bannerHomeAdapter.datas = bannerHomeDatas
                            bannerHomeAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })
    }

    private fun getFriendPicListFromServer(token: String, groupIdx: Int, page: Int) {

        placePicService.getInstance()
            .requestFriendPic(
                token = token,
                groupIdx = groupIdx,
                page = page
            ).enqueue(object : Callback<BaseResponse<FriendPicResponse>> {
                override fun onFailure(
                    call: retrofit2.Call<BaseResponse<FriendPicResponse>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: retrofit2.Call<BaseResponse<FriendPicResponse>>,
                    response: Response<BaseResponse<FriendPicResponse>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        Log.d("typeCheck", "통신성공")
                        progressbar_fp.visibility = View.GONE
                        if (response.body()!!.success) {
                            Log.d("typeCheck", "${response.body()!!.data.javaClass}")
                            for (i in response.body()!!.data.places.indices) {

                                totalPage = response.body()!!.data.totalPage
                                val pdate =
                                    DateParser(response.body()!!.data.places[i].placeCreatedAt)
                                val dateResult: String = pdate.calculateDiffDate() //UNIX 타임 변환

                                friendPicList.apply {
                                    add(
                                        FriendPicData(
                                            userIdx = response.body()!!.data.places[i].userIdx,
                                            placeIdx = response.body()!!.data.places[i].placeIdx,
                                            groupIdx = response.body()!!.data.places[i].groupIdx,
                                            userName = response.body()!!.data.places[i].userName,
                                            part = response.body()!!.data.places[i].part,
                                            profileImageUrl = response.body()!!.data.places[i].profileImageUrl,
                                            placeName = response.body()!!.data.places[i].placeName,
                                            placeReview = response.body()!!.data.places[i].placeReview,
                                            placeImageUrl = response.body()!!.data.places[i].placeImageUrl,
                                            placeCreatedAt = dateResult,
                                            subway = response.body()!!.data.places[i].subway,
                                            tag = response.body()!!.data.places[i].tag,
                                            likeCnt = response.body()!!.data.places[i].likeCnt
                                        )
                                    )
                                }
                            }
                            friendPicAdapter.addItems(friendPicList)
                            friendPicAdapter.notifyDataSetChanged()
                            isLoading = false
                        }
                    }
                }
            })
    }
}

