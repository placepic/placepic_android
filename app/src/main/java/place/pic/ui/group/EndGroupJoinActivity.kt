package place.pic.ui.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_end_group_join.*
import place.pic.R

class EndGroupJoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_group_join)
        init()
    }

    private fun init() {
        buttonEventMapping()
    }

    private fun buttonEventMapping(){
        btn_goto_group_list.setOnClickListener {
            val gotoGroupListIntent = Intent(this, GroupListActivity::class.java)
            startActivity(gotoGroupListIntent)
            finishAffinity()
        }
    }

    private fun bindEndGroupJoin(){

    }
}