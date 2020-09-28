package place.pic.ui.main.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group_change.*
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

        }
    }
}