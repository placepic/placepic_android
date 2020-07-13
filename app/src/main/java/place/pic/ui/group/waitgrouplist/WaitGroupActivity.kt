package place.pic.ui.group.waitgrouplist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_wait_group.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.extands.customEnqueue

class WaitGroupActivity : AppCompatActivity() {

    lateinit var waitGroupAdapter: WaitGroupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait_group)
        init()
    }

    private fun init() {
        rv_wait_group_list.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        requestToWaitGroupList()
        buttonEventMapping()
    }

    private fun buttonEventMapping() {
        img_wait_group_top_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdpater(list: List<ResponseGroupList>) {
        waitGroupAdapter =
            WaitGroupAdapter(list, this)
        rv_wait_group_list.adapter = waitGroupAdapter
    }

    private fun requestToWaitGroupList() {
        PlacepicAuthRepository.getInstance(this).userToken?.let {
            PlacePicService.getInstance()
                .requestGroupList(
                    token = it,
                    filter = "wait"
                ).customEnqueue(
                    onSuccess = { response ->
                        response.body()?.data?.let { list ->
                            setAdpater(list)
                        }
                    }
                )
        }
    }
}