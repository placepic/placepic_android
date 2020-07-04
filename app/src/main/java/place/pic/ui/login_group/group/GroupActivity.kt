package place.pic.ui.login_group.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import place.pic.R

class GroupActivity : AppCompatActivity(),View.OnClickListener {

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_group)
        init()
    }

    private fun init() {
        //fragmentTransaction.replace(R.id.vp_group_list_viewpager,GroupListEmptyFragment()).commitAllowingStateLoss()
    }


    override fun onClick(v: View?) {
    }
}