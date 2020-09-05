package place.pic.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_detail_view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_friend_pic.*
import place.pic.R
import place.pic.ui.tag.ChipFactory
import java.util.*

class HomeFragment : Fragment() {

    lateinit var friendPicAdapter: FriendPicAdapter
    lateinit var layoutManager: LinearLayoutManager

    //private val tagChipList = mutableListOf<Chip>()
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

        initRcv(view)

        loadDatas()

        //loadDatas()
        //loadDatas() 호출을 통해 infinite scroll을 위한 준비 완료
    }

    private fun initRcv(view : View) {
        friendPicAdapter = FriendPicAdapter()
        rv_friendPic.adapter = friendPicAdapter
    }

    /*private fun loadDatas() {
        data = stubUserList() as MutableList<UserData>
        userListAdapter.addItems(data)
    }*/

    /*private fun changeListToChip (keyword: List<String>) {
        keyword.forEach { text ->
            val chip = ChipFactory.createSmallChip(layoutInflater)
            //하나씩 chip 생성
            tagChipList.add(chip)
            chip.text = text
            fp_chipGroup.addView(chip)
        }
    }*/

    private fun loadDatas() {

        val subways = listOf<String>("합정역", "홍대입구역")
        val tags = listOf<String>("혼술하기좋은", "단체석있는", "내부화장실", "24시간")
        //tag text 리스트

        datas.apply {
            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = subways,
                    tags = tags,
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써.."
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜2",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = subways,
                    tags = tags,
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써.."
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜3",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = subways,
                    tags = tags,
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써.."
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜4",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = subways,
                    tags = tags,
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써.."
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜5",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = subways,
                    tags = tags,
                    uploadDate = Date(),
                    content = "영훈이의 픽.. 죽기전에 꼭 한번쯤은.. 먹어보고 싶다.. 인천대 명물 최영훈이 추천하는 그곳.. 인천대 명물 미부떡.. 나같은 떡볶이 광인은 먹어보고싶은그곳..나정말배고프다지금.. 아임헝그리... 눈물나.. 떡볶이 먹고싶어.. 리뷰는 몇줄까지 써.."
                )
            )
        }
        friendPicAdapter.datas = datas
        friendPicAdapter.notifyDataSetChanged()
    }
}