package place.pic.ui.main.detail.liker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_liker_user_list.*
import place.pic.R
import place.pic.data.remote.response.DetailLikerResponse

class LikerUserListActivity : AppCompatActivity() {

    private lateinit var likerList:DetailLikerResponse

    private lateinit var likerUserListAdapter:LikerUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liker_user_list)
        likerList = intent.getParcelableExtra("List")!!
        init()
    }

    private fun init(){
        setAdapter()
        buttonEventMapping()
    }

    private fun buttonEventMapping(){
        img_btn_liker_user_list_top_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdapter(){
        likerUserListAdapter = LikerUserListAdapter(this,likerList.likeList)
        rv_liker_user_list.adapter = likerUserListAdapter
    }
}