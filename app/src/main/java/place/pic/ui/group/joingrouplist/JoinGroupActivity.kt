package place.pic.ui.group.joingrouplist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_join_group.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.extands.customEnqueue

class JoinGroupActivity : AppCompatActivity() {

    lateinit var joinGroupListAdapter: JoinGroupListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_group)
        init()
    }

    private fun init() {
        requestToJoinGroupList()
        buttonMapping()
    }

    private fun buttonMapping() {
        img_join_group_top_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdapter(list:List<ResponseGroupList>) {
        joinGroupListAdapter =
            JoinGroupListAdapter(
                list,
                this
            )
        rv_join_group_list.adapter = joinGroupListAdapter
    }

    /*서버 연결*/
    private fun requestToJoinGroupList() {
        PlacepicAuthRepository.getInstance(this).userToken?.let {
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


}