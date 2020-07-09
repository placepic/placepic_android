package place.pic.ui.main.mypage.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_wait_user_list.*
import place.pic.R

class WaitUserListActivity : AppCompatActivity() {

    private val userListData = mutableListOf<UserListData>()

    lateinit var waitUserListAdapter: WaitUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait_user_list)
        init()
    }

    private fun init(){
        loadTestUserData() 
        rv_wait_user_list.addItemDecoration(
            DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        )
        setAdapter()
    }

    private fun setAdapter(){
        waitUserListAdapter = WaitUserListAdapter(userListData,this)
        rv_wait_user_list.adapter = waitUserListAdapter
    }

    private fun loadTestUserData(){
        userListData.apply {
            add(
                UserListData(
                    "조희연",
                    "27기 부회장"
                )
            )
            add(
                UserListData(
                    "이정연",
                    "26기 디자인"
                )
            )
            add(
                UserListData(
                    "윤혁",
                    "27기 안팟장"
                )
            )
            add(
                UserListData(
                    "배민주",
                    "27기 디팟장"
                )
            )
            add(
                UserListData(
                    "김한솔",
                    "27기 기팟장"
                )
            )
        }
    }
}