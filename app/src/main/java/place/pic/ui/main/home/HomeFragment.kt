package place.pic.ui.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import place.pic.R
import place.pic.ui.main.home.banner.BannerHomeAdapter
import place.pic.ui.main.home.banner.BannerHomeData
import place.pic.ui.main.home.banner.BannerListActivity
import place.pic.ui.main.home.friendpic.FriendPicAdapter
import place.pic.ui.main.home.friendpic.FriendPicData
import place.pic.ui.util.DateParser

class HomeFragment : Fragment() {

    lateinit var bannerHomeAdapter: BannerHomeAdapter
    lateinit var friendPicAdapter: FriendPicAdapter
    lateinit var layoutManager: LinearLayoutManager

    val bannerDatas = mutableListOf<BannerHomeData>()
    val friendPicDatas = mutableListOf<FriendPicData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
        initRcv()

        loadBannerDatas()
        loadFriendPicDatas()
        //loadDatas() 호출을 통해 infinite scroll을 위한 준비 완료

        img_btn_banner_list.setOnClickListener {
            val intent = Intent(context, BannerListActivity::class.java)
            startActivity(intent)
        }
    }

   private fun initViewPager() {
        bannerHomeAdapter = BannerHomeAdapter()
        vp_banner.adapter = bannerHomeAdapter
    }

    private fun initRcv() {
        friendPicAdapter = FriendPicAdapter()
        rv_friendPic.adapter = friendPicAdapter
    }

    /*private fun loadDatas() {
        data = stubUserList() as MutableList<FriendPicData>
        friendPicAdapter.addItems(data)
    }

    private fun getFriendPicListFromServer() {} */


    private fun loadBannerDatas() {
        bannerDatas.apply {
            add(
                BannerHomeData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA",
                    count = "1 / 3"
                )
            )

            add(
                BannerHomeData(
                    badgeBg = "#5BC9A1",
                    badge = "PICK",
                    title = "제목 테스트",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://images.homify.com/c_fill,f_auto,q_0,w_740/v1495806612/p/photo/image/2031170/%C3%A3_-_%C3%A3%C3%B5_%C3%8E_%C3%9B%C3%80%C3%BA%C3%81%C3%95%C3%81_%C3%BA_8.jpg",
                    count = "2 / 3"
                )
            )

            add(
                BannerHomeData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "알고보니 리사이클러가 안되는중",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg",
                    count = "3 / 3"
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
                    userName = "김다혜",
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

            add(
                FriendPicData(
                    profileImageUrl = "https://img.hankyung.com/photo/201911/03.20725204.1.jpg",
                    userName = "최영훈",
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

            add(
                FriendPicData(
                    profileImageUrl = "https://img.hankyung.com/photo/201911/03.20725204.1.jpg",
                    userName = "배민주",
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

            add(
                FriendPicData(
                    profileImageUrl = "https://img.hankyung.com/photo/201911/03.20725204.1.jpg",
                    userName = "이정연",
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
        friendPicAdapter.datas = friendPicDatas
        friendPicAdapter.notifyDataSetChanged()
    }
}