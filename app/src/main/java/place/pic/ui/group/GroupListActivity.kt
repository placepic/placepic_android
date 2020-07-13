package place.pic.ui.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_group_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.ResponseGroupList
import place.pic.databinding.ActivityGroupListBinding

import place.pic.ui.extands.customEnqueue
import place.pic.ui.group.joingrouplist.JoinGroupActivity
import place.pic.ui.group.waitgrouplist.WaitGroupActivity

class GroupListActivity : AppCompatActivity() {

    private val transaction = supportFragmentManager.beginTransaction()

    private var groupListData:List<ResponseGroupList>? = null
    private var groupWaitListData:List<ResponseGroupList>? = null

    lateinit var binding: ActivityGroupListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_group_list)
        binding.waitGroupCount = "0"
        init()
    }

    private fun init() {
        requestToServer()
        buttonEventMapping()
    }

    private fun groupListSelecter(){
        groupListData?.let {list->
            if (list.isNotEmpty()) {
                loadExistGroup()
                return
            }
        }
        loadEmptyGroup()
    }

    private fun requestToServer(){
        requestToMyGroupList()
        requestToWaitGroupList()
    }

    private fun buttonEventMapping() {
        cl_btn_wait_group_list.setOnClickListener {
            val gotoWaitGroupIntent = Intent(this, WaitGroupActivity::class.java)
            startActivity(gotoWaitGroupIntent)
        }
    }

    private fun loadEmptyGroup() {
        Log.d("그룹 리스트", "빈그룹")
        tv_btn_join_group.visibility = View.GONE
        val emptyGroupFragment = EmptyGroupListFragment()
        emptyGroupFragment.setJoinButtonClickListener {
            gotoJoinActivity()
        }
        loadGroupFragment(emptyGroupFragment)
    }

    private fun loadExistGroup() {
        Log.d("그룹 리스트", "그룹 리스트")
        tv_btn_join_group.visibility = View.VISIBLE
        tv_btn_join_group.setOnClickListener {
            gotoJoinActivity()
        }
        loadGroupFragment(
            ExistGroupListFragment(groupListData!!)
        )
    }

    private fun gotoJoinActivity() {
        val gotoJoinIntent = Intent(this, JoinGroupActivity::class.java)
        startActivity(gotoJoinIntent)
    }

    private fun loadGroupFragment(fragment: Fragment) {
        transaction.replace(R.id.frame_group_list, fragment)
        transaction.commit()
    }

    private fun haveWaitGroupList(){
        groupWaitListData?.let {
            if (it.isNotEmpty()) {
                Log.d("WaitDataList", "데이터 있음")
                enableWaitGroupListButton()
                waitListTextMapping()
                return
            }
            Log.d("WaitDataList", "데이터 없음")
            disableWaitGroupListButton()
        }
    }

    private fun enableWaitGroupListButton() {
        Log.d("승인 대기 버튼 테스트", "버튼 활성화")
        cl_wait_group.visibility = View.VISIBLE
    }

    private fun disableWaitGroupListButton() {
        Log.d("승인 대기 버튼 테스트", "버튼 비활성화")
        cl_wait_group.visibility = View.GONE
    }

    private fun waitListTextMapping() {
        groupWaitListData?.let {
            binding.waitGroupCount = "승인 대기중인 그룹("+it.size.toString()+")"
        }
    }

    /*서버 통신*/
    private fun requestToMyGroupList(){
        PlacepicAuthRepository.getInstance(this).userToken?.let { it ->
            PlacePicService.getInstance()
                .requestMyGroupList(
                    token = it
                )
                .customEnqueue(
                    onSuccess = {response ->
                        groupListData = response.body()?.data
                        groupListSelecter()
                    }
                )
        }
    }
    private fun requestToWaitGroupList(){
        PlacepicAuthRepository.getInstance(this).userToken?.let {
            PlacePicService.getInstance()
                .requestGroupList(
                    token = it,
                    filter = "wait"
                )
                .customEnqueue(
                    onSuccess = {response ->
                        groupWaitListData = response.body()?.data
                        haveWaitGroupList()
                    }
                )
        }
    }
}