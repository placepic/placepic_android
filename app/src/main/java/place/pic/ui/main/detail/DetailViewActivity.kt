package place.pic.ui.main.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_view.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Place
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.DetailResponse
import place.pic.data.remote.response.Uploader
import place.pic.ui.extands.customEnqueue
import place.pic.ui.tag.ChipFactory
import java.text.SimpleDateFormat
import java.util.*

/*
 * 글 작성 유저와 글의 유저 Id를 비교하여 글 삭제 버튼의 유무를 지정하기 위해서
 * putExtra에 "userIdx" 키로 userIdx:Int를 넘겨주시면 됩니다.
 *
 * list item을 클릭하면 해당 아이템의 Detail이 불려야하므로
 * put Extra에 "placeIdx" 키로 placeIdx:Int 를 넘겨 주시면 됩니다.
 *
 * 각각의 키로는 Int로 캐스팅하여 사용합니다.
 */

class DetailViewActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var detailviewPagerAdapter: DetailViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        init()
    }

    private fun init(){
        requestToDetailView()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.img_btn_detail_top_back ->{
                onBackPressed()
            }
        }
    }

    /* 서버 연결 */
    private fun requestToDetailView(){
        val token = PlacepicAuthRepository.getInstance(this).userToken?: return

        PlacePicService
            .getInstance()
            .requestDetail(
                token = token,
                placeIdx = 63
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
        insertDateTime(detailResponse.placeCreatedAt.toLong())
        val title = detailResponse.placeName
        tv_detail_title.text = title
        tv_detail_top_title.text = title
        tv_detail_content.text = detailResponse.placeReview
        insertCategory(detailResponse.categoryIdx)
        tv_detail_subway_info.text = detailStringForm(detailResponse.subway,"/")
        tv_detail_address_info.text = detailResponse.placeRoadAddress
        tv_detail_place_info.text = detailStringForm(detailResponse.placeInfo," · ")
        tv_detail_shared_people_count.text = detailResponse.likeCount.toString()

    }

    private fun insertUploadUserDataInView(uploader: Uploader) {
        Glide.with(this).load(uploader.profileImageUrl).into(img_detail_user_profile)
        tv_detail_user_name.text = uploader.userName
        tv_detail_user_part.text = uploader.part
        tv_detail_user_post_count.text = ("작성한 글 ${uploader.postCount}")
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

    private fun insertDateTime(placeCreatedAt: Long) {
        tv_detail_user_create_at.text = unixDateTimeParser(placeCreatedAt)

    }

    private fun unixDateTimeParser(placeCreatedAt: Long): String {
        val date = Date(placeCreatedAt * 1000L)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd",Locale.KOREA)
        val formattingDate = simpleDateFormat.format(date)
        return formattingDate
    }

    private fun insertCategory(categoryIdx: Int) {
        val category = Place.Type.findByPosition(categoryIdx).value
        tv_detail_category_info.text = category
    }

    private fun detailStringForm(stringList:List<String>,dividerString:String): String {

        val detailStringBuilder = StringBuilder("")

        stringList.forEach {str->
            if (str != stringList[0]) {
                detailStringBuilder.append(dividerString)
                detailStringBuilder.append(str)
            } else {
                detailStringBuilder.append(str)
            }
        }

        return detailStringBuilder.toString()
    }
}