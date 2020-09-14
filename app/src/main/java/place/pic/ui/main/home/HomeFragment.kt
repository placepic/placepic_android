package place.pic.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import place.pic.R
import place.pic.data.entity.KeywordTag
import java.util.*

class HomeFragment : Fragment() {

    lateinit var bannerAdapter: BannerAdapter
    lateinit var friendPicAdapter: FriendPicAdapter
    lateinit var layoutManager: LinearLayoutManager

    private val tagChipList = mutableListOf<KeywordTag>()
    val datas = mutableListOf<FriendPicData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager(view)
        initRcv(view)

        loadDatas()
        //loadDatas() 호출을 통해 infinite scroll을 위한 준비 완료
    }

    private fun initViewPager(view: View) {
        bannerAdapter = BannerAdapter()
        vp_banner.adapter = bannerAdapter
    }

    private fun initRcv(view: View) {
        friendPicAdapter = FriendPicAdapter()
        rv_friendPic.adapter = friendPicAdapter
    }

    /*private fun loadDatas() {
        data = stubUserList() as MutableList<FriendPicData>
        friendPicAdapter.addItems(data)
    }

    private fun getFriendPicListFromServer() {} */


    private fun loadDatas() {

        datas.apply {
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
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써야할까요오오오오오오오"
                )
            )

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
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써야할까요오오오오오오오"
                )
            )

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
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써야할까요오오오오오오오"
                )
            )

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
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써야할까요오오오오오오오"
                )
            )
        }

        friendPicAdapter.datas = datas
        friendPicAdapter.notifyDataSetChanged()
    }
}