package place.pic.ui.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_group_change.*
import kotlinx.android.synthetic.main.activity_join_group.*
import kotlinx.android.synthetic.main.activity_join_group.rv_join_group_list
import kotlinx.android.synthetic.main.fragment_admin.view.*
import kotlinx.android.synthetic.main.fragment_group_change.*
import kotlinx.android.synthetic.main.fragment_group_change.view.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.group.joingrouplist.JoinGroupListAdapter
import place.pic.ui.main.MainActivity
import place.pic.ui.util.customEnqueue


class GroupChangeFragment : Fragment() {
    /*
    companion object {
        fun newInstance(): GroupChangeFragment {
            return GroupChangeFragment()
        }
    }

    private lateinit var joinGroupListAdapter: JoinGroupListAdapter

    private fun init() {
        requestToJoinGroupList()
    }

    private fun setAdapter(list:List<ResponseGroupList>) {
        joinGroupListAdapter =
            JoinGroupListAdapter(
                list,MainActivity()
            )
        rv_group_list.adapter = joinGroupListAdapter
    }

    /*서버 연결*/
    private fun requestToJoinGroupList() {
        PlacepicAuthRepository.getInstance(MainActivity()).userToken?.let {
            PlacePicService.getInstance()
                .requestGroupApplyList(
                    token = it
                )
                .customEnqueue(
                    onSuccess = {response ->
                        response.body()?.data?.let { list->
                            setAdapter(list)
                        }
                    }
                )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        init()
        val v = inflater.inflate(R.layout.fragment_group_change, container, false)

        /*v.img_groupchange_top_bar_back_btn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
         */
        return v
    }

 */
}