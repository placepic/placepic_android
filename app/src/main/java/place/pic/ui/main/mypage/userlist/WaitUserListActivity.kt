package place.pic.ui.main.mypage.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_wait_user_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.ResponseWaitUser
import place.pic.ui.extands.customEnqueue

class WaitUserListActivity : AppCompatActivity() {

    private lateinit var waitUserListAdapter: WaitUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait_user_list)
        init()
    }

    private fun init() {
        requestToWaitUserList()
    }

    private fun setAdapter(list: List<ResponseWaitUser>) {
        waitUserListAdapter = WaitUserListAdapter(list, this)
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
                        setAdapter(list)
                    }
                }
            )
    }
}