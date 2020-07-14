package place.pic.ui.main.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.DetailResponse
import place.pic.ui.extands.customEnqueue

class DetailViewActivity : AppCompatActivity() {

    private var imageList:List<String>? = null

    lateinit var detailviewPagerAdapter: DetailViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        init()
    }

    private fun init(){

    }

    /* 서버 연결 */
    private fun requestToDetailView(){
        val token = PlacepicAuthRepository.getInstance(this).userToken?: return

        PlacePicService
            .getInstance()
            .requestDetail(
                token = token,
                placeIdx = 60
            ).customEnqueue(
                onSuccess = {response ->
                    val serverData = response.body()?.data?:return@customEnqueue
                    bindingDetail(serverData)
                }
            )
    }

    private fun bindingDetail(detailResponse: DetailResponse) {
        viewPager = (ViewPager) findViewById(R.id.view);
        adapter = new Adapter(this);
        viewPager.setAdapter(adapter);
    }

    private fun setViewPager(){

    }
}