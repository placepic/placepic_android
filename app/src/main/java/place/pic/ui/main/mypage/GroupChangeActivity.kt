package place.pic.ui.main.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group_change.*
import kotlinx.android.synthetic.main.activity_signup.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.group.BindGroupListEvent
import place.pic.ui.group.GroupListFragment
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customEnqueue

class GroupChangeActivity : AppCompatActivity(), BindGroupListEvent {

    private val groupListData: MutableList<ResponseGroupList> = mutableListOf()
    private var groupListFragment = GroupListFragment()

    override fun requestToGroupListData() {
        loadData()
        groupListFragment.setAdapter(groupListData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_change)
        val transaction = supportFragmentManager.beginTransaction()
        img_groupchange_top_bar_back_btn.setOnClickListener {
            finish()
        }
        transaction.add(R.id.frame_group_list,groupListFragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
    }

    private fun loadData() {
        groupListData.apply {
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "안녕하세용",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 1,
                    userIdx = 8
                )
            )
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "진수진짜최고",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 1,
                    userIdx = 8
                )
            )
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "짱짱맨",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 2,
                    userIdx = 8
                )
            )
        }
    }
}