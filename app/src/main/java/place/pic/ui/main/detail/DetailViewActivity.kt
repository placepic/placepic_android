package place.pic.ui.main.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_view.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.DetailResponse
import place.pic.data.remote.response.Uploader
import place.pic.ui.extands.customEnqueue
import place.pic.ui.tag.ChipFactory

class DetailViewActivity : AppCompatActivity() {

    private lateinit var detailviewPagerAdapter: DetailViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        init()
    }

    private fun init(){

        requestToDetailView()
    }

    /* 서버 연결 */
    private fun requestToDetailView(){
        val token = PlacepicAuthRepository.getInstance(this).userToken?: return

        PlacePicService
            .getInstance()
            .requestDetail(
                token = token,
                placeIdx = 95
            ).customEnqueue(
                onSuccess = {response ->
                    val serverData = response.body()?.data?:return@customEnqueue
                    bindingDetail(serverData)
                }
            )
    }

    private fun bindingDetail(detailResponse: DetailResponse) {
        insertUploadUserDataInView(detailResponse.uploader)
        insertImageInViewPager(detailResponse.imageUrl)
        insertKeywordInDetailChip(detailResponse.keyword)
        tv_detail_title.text = detailResponse.placeName
        //tv_detail_user_create_at.text = detailResponse.placeCreatedAt

    }

    private fun insertUploadUserDataInView(uploader: Uploader) {
        Glide.with(this).load(uploader.profileImageUrl).into(img_detail_user_profile)
        tv_detail_user_name.text = uploader.userName
        tv_detail_user_part.text = uploader.part
        tv_detail_user_post_count.text = uploader.postCount.toString()
    }

    private fun insertImageInViewPager(imageList:List<String>){
        detailviewPagerAdapter = DetailViewPagerAdapter(this,imageList)
        vp_detail_image_slide.adapter = detailviewPagerAdapter
    }

    private fun insertKeywordInDetailChip(keyword: List<String>) {
        keyword.forEach() { text ->
            val chip = ChipFactory.createDetailChip(layoutInflater)
            chip.text = text
            chip_group_useful_info.addView(chip)
        }
    }
}