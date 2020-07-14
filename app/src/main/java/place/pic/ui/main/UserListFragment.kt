package place.pic.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_user_list.*
import place.pic.R
import place.pic.ui.main.mypage.userlist.UserListAdapter
import place.pic.ui.main.mypage.userlist.UserListData
import place.pic.ui.main.mypage.userlist.stubUserList


class UserListFragment : Fragment() {

    lateinit var userListAdapter: UserListAdapter
    lateinit var layoutManager: LinearLayoutManager
    private var datas: MutableList<UserListData> = mutableListOf()

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
        
        //loadDatas() 호출을 통해 infinite scroll을 위한 준비 완료
    }

    private fun initRcv(view: View) {
        userListAdapter = UserListAdapter()
        recyclerview_user_list.adapter = userListAdapter
    }

    private fun loadDatas() {
        datas = stubUserList() as MutableList<UserListData>
        userListAdapter.addItems(datas)
    }
}