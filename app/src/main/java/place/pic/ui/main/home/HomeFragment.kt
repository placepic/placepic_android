package place.pic.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import place.pic.R
import java.util.*

class HomeFragment : Fragment() {

    lateinit var friendPicAdapter: FriendPicAdapter
    lateinit var layoutManager: LinearLayoutManager
    val datas : MutableList<FriendPicData> = mutableListOf<FriendPicData>()

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

    private fun loadDatas() {
        datas.apply {
            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = emptyList(),
                    uploadDate = Date()
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜2",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = emptyList(),
                    uploadDate = Date()
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜3",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = emptyList(),
                    uploadDate = Date()
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜4",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = emptyList(),
                    uploadDate = Date()
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜5",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = emptyList(),
                    uploadDate = Date()
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜6",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = emptyList(),
                    uploadDate = Date()
                )
            )

            add(
                FriendPicData(
                    profileImageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    userName = "김다혜7",
                    part = "27기 안드로이드파트",
                    imageUrl = "https://t1.daumcdn.net/cfile/tistory/2443D04556A6314F0A",
                    name = "진성한우곱창",
                    subways = emptyList(),
                    uploadDate = Date()
                )
            )
        }
        friendPicAdapter.datas = datas
        friendPicAdapter.notifyDataSetChanged()
    }
}