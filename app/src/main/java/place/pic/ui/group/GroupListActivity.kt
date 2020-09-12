package place.pic.ui.group

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_group_list.*
import place.pic.R

class GroupListActivity : AppCompatActivity() {

    private val transaction = supportFragmentManager.beginTransaction()

    private var groupListFragment = ExistGroupListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_list)
        requestGroupList()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame_group_list,groupListFragment)
        transaction.commit()
    }


    private fun requestGroupList(){
        groupListFragment.setRequestGroupListListener {requestToServerGroupListEvent()}
    }

    private fun requestToServerGroupListEvent(){

    }
}