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

    private val userListData = mutableListOf<UserListData>()

    lateinit var waitUserListAdapter: WaitUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait_user_list)
        init()
    }

    private fun init(){
        requestToWaitUserList()
    }

    private fun setAdapter(list: List<ResponseWaitUser>){
        waitUserListAdapter = WaitUserListAdapter(list,this)
        rv_wait_user_list.adapter = waitUserListAdapter
    }

    /*request to Server*/
    //TODO : groupIdx 저장하는 부분이 생기면 해당 아이디 불러오기
    private fun requestToWaitUserList(){
        PlacepicAuthRepository.getInstance(this).userToken?.let { token ->
            PlacePicService.getInstance().requestWaitGroupList(
                token = token,
                groupIdx = 3
            ).customEnqueue(
                onSuccess = {response ->
                    response.body()?.data?.let {list ->
                        setAdapter(list)
                    }
                }
            )
        }
    }
}