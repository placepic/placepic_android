package place.pic.ui.main.detail.liker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_liker_user_list.*
import place.pic.R
import place.pic.data.remote.response.Like

class LikerUserListActivity : AppCompatActivity() {

    private var likerList = intent.getSerializableExtra("Like")

    private lateinit var likerUserListAdapter:LikerUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liker_user_list)
        init()
    }

    private fun init(){
        setAdapter()
    }

    private fun setAdapter(){
        likerUserListAdapter = LikerUserListAdapter(this,likerList as List<Like>)
        rv_liker_user_list.adapter = likerUserListAdapter
    }
}