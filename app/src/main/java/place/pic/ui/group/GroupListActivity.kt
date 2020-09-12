package place.pic.ui.group

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import place.pic.R
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation

class GroupListActivity : AppCompatActivity(),BindGroupListEvent {

    private val groupListData: MutableList<ResponseGroupList> = mutableListOf()
    private var groupListFragment = GroupListFragment()

    override fun requestToGroupListData() {
        loadData()
        groupListFragment.setAdapter(groupListData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_list)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame_group_list,groupListFragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        previousActivityAnimation()
    }

    override fun finish() {
        super.finish()
        nextActivityAnimation()
    }



    private fun loadData() {
        groupListData.apply {
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "가입된 그룹",
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
                    groupName = "가입된 그룹",
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
                    groupName = "미가입 그룹",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 2,
                    userIdx = 8
                )
            )
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "가입된 그룹",
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
                    groupName = "미가입 그룹",
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