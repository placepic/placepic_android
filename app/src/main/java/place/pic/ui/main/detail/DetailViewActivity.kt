package place.pic.ui.main.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_view.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Place
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.DetailLikerResponse
import place.pic.data.remote.response.DetailResponse
import place.pic.data.remote.response.Like
import place.pic.data.remote.response.Uploader
import place.pic.ui.extands.customEnqueue
import place.pic.ui.extands.unixDateTimeParser
import place.pic.ui.main.detail.liker.LikerUserListActivity
import place.pic.ui.tag.ChipFactory

/*
* 글 작성 유저와 글의 장소 ㄹId를 비교하여 글 삭제 버튼의 유무를 지정하기 위해서
* putExtra에 "placeIdx" 키로 placeIdx:Int를 넘겨주시면 됩니다.
*
* 각각의 키로는 Int로 캐스팅하여 사용합니다.
*/

class DetailViewActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var detailviewPagerAdapter: DetailViewPagerAdapter

    private lateinit var likerUserList : ArrayList<Like>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        init()
    }

    private fun init(){
        requestToDetailView()
        buttonEventMapping()
    }

    private fun buttonEventMapping(){
        cl_btn_detail_shared_people.setOnClickListener(this)
        cl_btn_detail_like.setOnClickListener(this)
        cl_btn_detail_more_info.setOnClickListener(this)
        cl_btn_detail_like.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.img_btn_detail_top_back ->{
                onBackPressed()
            }
            R.id.cl_btn_detail_shared_people -> {
                val gotoLikerUserList =
                    Intent(this,LikerUserListActivity::class.java)
                gotoLikerUserList.putExtra("List",DetailLikerResponse(likerUserList))
                startActivity(gotoLikerUserList)
            }
            R.id.cl_btn_detail_like ->{

            }
            R.id.img_btn_detail_bookmark -> {

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


    /*서버 연결시 뷰에 뿌려주는 함수 작업.*/
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
        tv_detail_bookmark_count.text = detailResponse.bookmarkCount.toString()
        likerUserList = detailResponse.likeList as ArrayList<Like>
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