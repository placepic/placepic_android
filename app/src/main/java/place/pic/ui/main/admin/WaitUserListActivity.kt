package place.pic.ui.main.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_wait_user_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.ResponseWaitUser
import place.pic.ui.util.customEnqueue

class WaitUserListActivity : AppCompatActivity() {

    private lateinit var waitUserListAdapter: WaitUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait_user_list)
        buttonEventMapping()
    }

    /* TODO 리사이클러뷰 수정하기.
     지금 작성해둔 방법이 좋은 방법은 아니라고 생각됨.
     지금은 생명주기에서 해당 뷰가 보이기 전마다 통신을 하는데.
     서버 통신 횟수가 많아서 비효율이라고 생각함.*/
    override fun onResume() {
        super.onResume()
        init()
    }

    private fun init() {
        requestToWaitUserList()
    }

    private fun buttonEventMapping(){
        img_wait_user_list_top_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdapter(list: List<ResponseWaitUser>) {
        waitUserListAdapter =
            WaitUserListAdapter(list, this)
        rv_wait_user_list.adapter = waitUserListAdapter
    }

    /*request to Server*/
    private fun requestToWaitUserList() {
        val token = PlacepicAuthRepository.getInstance(this).userToken ?: return
        val groupIdx = PlacepicAuthRepository.getInstance(this).groupId?: return

        PlacePicService
            .getInstance()
            .requestWaitGroupList(
                token = token,
                groupIdx = groupIdx
            ).customEnqueue(
                onSuccess = { response ->
                    response.body()?.data?.let { list ->
                        selectUserListView(list)
                    }
                }
            )
    }

    private fun selectUserListView(list: List<ResponseWaitUser>){
        if (list.isNullOrEmpty()) {
            goneWaitUserList()
            visibleEmptyWaitUserList()
            return
        }
        visibleWaitUserList()
        setAdapter(list)
        goneEmptyWaitUserList()
    }

    private fun goneWaitUserList() {
        rv_wait_user_list.visibility = View.GONE
    }
    private fun visibleWaitUserList() {
        rv_wait_user_list.visibility = View.VISIBLE
    }

    private fun goneEmptyWaitUserList(){
        cl_empty_wait_group.visibility = View.GONE
    }
    private fun visibleEmptyWaitUserList(){
        cl_empty_wait_group.visibility = View.VISIBLE
    }
}