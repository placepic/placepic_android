package place.pic.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_user_list.*
import place.pic.R
import place.pic.ui.main.mypage.userlist.UserListAdapter
import place.pic.ui.main.mypage.userlist.UserListData


class UserListFragment : Fragment() {

    lateinit var userListAdapter: UserListAdapter
    lateinit var layoutManager: LinearLayoutManager
    val datas: MutableList<UserListData> = mutableListOf<UserListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcv(view)
        loadDatas()
        //데이터를 임의로 생성하고 어댑터에 전달해주겠습니다!
    }

    private fun initRcv(view: View) {
        userListAdapter = UserListAdapter(view.context)
        recyclerview_user_list.adapter = userListAdapter
        //recyclerView의 어댑터를 instaAdapter로 지정
        recyclerview_user_list.apply {
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    private fun loadDatas() {
        datas.apply {
            add(
                UserListData(
                    userName = "김다혜",
                    part = "다혜왕자",
                    postCount = 8
                ))

            add(
                UserListData(
                    userName = "이수정",
                    part = "공주님",
                    postCount = 5
                ))

            add(
                UserListData(
                    userName = "유희수",
                    part = "마이러버 ㅎㅎ",
                    postCount = 4
                ))

            add(
                UserListData(
                    userName = "이연정",
                    part = "아버지",
                    postCount = 4
                ))

            add(
                UserListData(
                    userName = "조희연",
                    part = "딸",
                    postCount = 3
                ))

            add(
                UserListData(
                    userName = "김동관",
                    part = "관동",
                    postCount = 2
                ))

            add(
                UserListData(
                    userName = "홍준엽",
                    part = "레드시키",
                    postCount = 1
                ))

            add(
                UserListData(
                    userName = "최영훈",
                    part = "영훈왕자",
                    postCount = 0
                ))

            add(
                UserListData(
                    userName = "윤혁",
                    part = "안드아버지",
                    postCount = 0
                ))

        }
        //AndroidMenifest.xml에서 uses-permission INTERNET 설정을 해줘야만 이미지 로드 가능

        userListAdapter.datas = datas
        userListAdapter.notifyDataSetChanged()
        //데이터가 갱신되었음을 Adapter에게 알려주는 역할
    }
}