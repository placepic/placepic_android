package place.pic.ui.main.detail.liker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_liker_user_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.Like
import place.pic.ui.util.customEnqueue

class LikerUserListActivity : AppCompatActivity() {

    private var placeIdx:Long = 0L

    private lateinit var likerUserListAdapter:LikerUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liker_user_list)
        placeIdx = intent.getLongExtra("placeIdx",0)
        init()
    }

    private fun init(){
        requestToShardUserList()
        buttonEventMapping()
    }

    private fun buttonEventMapping(){
        img_btn_liker_user_list_top_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdapter(list:List<Like>){
        likerUserListAdapter = LikerUserListAdapter(this,list)
        rv_liker_user_list.adapter = likerUserListAdapter
    }

    private fun selectLikerListView(list: List<Like>) {
        if (list.isNullOrEmpty()) {
            cl_empty_shared_user.visibility = View.VISIBLE
            rv_liker_user_list.visibility = View.GONE
            return
        }
        cl_empty_shared_user.visibility = View.GONE
        rv_liker_user_list.visibility = View.VISIBLE
        setAdapter(list)
    }

    private fun requestToShardUserList(){
        val token = PlacepicAuthRepository.getInstance(this).userToken?:return

        PlacePicService.getInstance()
            .requestToLikeList(
                token = token,
                placeIdx = placeIdx
            ).customEnqueue(
                onSuccess = {response ->
                    selectLikerListView(
                        response.body()?.data!!
                    )
                }
            )
    }
}