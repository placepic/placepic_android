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
import place.pic.data.remote.request.RequestToPlacceIdx
import place.pic.data.remote.response.DetailResponse
import place.pic.data.remote.response.Uploader
import place.pic.ui.util.customEnqueue
import place.pic.ui.util.unixDateTimeParser
import place.pic.ui.main.detail.liker.LikerUserListActivity
import place.pic.ui.tag.ChipFactory
import place.pic.ui.webview.InWebActivity

/*
* 글 작성 유저와 글의 장소 ㄹId를 비교하여 글 삭제 버튼의 유무를 지정하기 위해서
* putExtra에 "placeIdx" 키로 placeIdx:Int를 넘겨주시면 됩니다.
*
* 각각의 키로는 Int로 캐스팅하여 사용합니다.
*/

class DetailViewActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var detailviewPagerAdapter: DetailViewPagerAdapter

    private lateinit var token: String

    private var placeIdx: Long = 0

    private var webUrl: String = ""
    private var webTitle: String = ""

    private var likeCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        token = PlacepicAuthRepository.getInstance(this).userToken ?: return
        placeIdx = intent.getLongExtra("placeIdx", 0)
        init()
    }

    private fun init() {
        requestToDetailView()
        buttonEventMapping()
    }

    private fun buttonEventMapping() {
        img_btn_detail_top_back.setOnClickListener(this)
        cl_btn_detail_shared_people.setOnClickListener(this)
        cl_btn_detail_like.setOnClickListener(this)
        cl_btn_detail_more_info.setOnClickListener(this)
        cl_btn_detail_like.setOnClickListener(this)
        img_btn_detail_bookmark.setOnClickListener(this)
        tv_btn_detail_top_del.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.img_btn_detail_top_back -> {
                onBackPressed()
            }
            R.id.cl_btn_detail_shared_people -> {
                val gotoLikerUserList =
                    Intent(this, LikerUserListActivity::class.java)
                gotoLikerUserList.putExtra("placeIdx", placeIdx)
                startActivity(gotoLikerUserList)
            }
            R.id.tv_btn_detail_top_del -> {
                //TODO 글 삭제시 PlaceList 수정이 필요함.
                requestToDeletePlace()
            }
            R.id.cl_btn_detail_like -> {
                setLikeButtonClickEvent()
            }
            R.id.img_btn_detail_bookmark -> {
                setBookmarkButtonClickEvent()
            }
            R.id.cl_btn_detail_more_info -> {
                val gotoWebViewIntent = Intent(this, InWebActivity::class.java)
                gotoWebViewIntent.putExtra("webUrl", webUrl)
                gotoWebViewIntent.putExtra("webTitle", webTitle)
                startActivity(gotoWebViewIntent)
            }
        }
    }


    /* 서버 연결 */
    //리스트 불러오기
    private fun requestToDetailView() {
        PlacePicService.getInstance()
            .requestDetail(
                token = token,
                placeIdx = placeIdx
            ).customEnqueue(
                onSuccess = { response ->
                    val serverData = response.body()?.data ?: return@customEnqueue
                    bindingDetail(serverData)
                }
            )
    }

    //좋아요 관련 서버 연결
    private fun requestToLike() {
        PlacePicService.getInstance()
            .requestToLike(
                token = token,
                body = RequestToPlacceIdx(placeIdx)
            ).customEnqueue(
                onSuccess = { _ ->
                    setMyLikeButtonStatus(true)
                }
            )
    }

    private fun requestToDeleteLike() {
        PlacePicService.getInstance()
            .requestToDelLike(
                token = token,
                placeIdx = placeIdx
            ).customEnqueue(
                onSuccess = { _ ->
                    setMyLikeButtonStatus(false)
                }
            )
    }

    //북마크 서버연결
    private fun requestToBookmark() {
        PlacePicService.getInstance()
            .requestToBookmark(
                token = token,
                body = RequestToPlacceIdx(placeIdx)
            ).customEnqueue(
                onSuccess = {
                    setMyBookmarkButtonStatus(true)
                }
            )
    }

    private fun requestToDeleteBookmark() {
        PlacePicService.getInstance()
            .requestToDelBookmark(
                token = token,
                placeIdx = placeIdx
            ).customEnqueue(
                onSuccess = {
                    setMyBookmarkButtonStatus(false)
                }
            )
    }

    //글 삭제 서버 연결
    private fun requestToDeletePlace() {
        PlacePicService.getInstance()
            .requestToDeletePlace(
                token = token,
                placeIdx = placeIdx
            ).customEnqueue(
                onSuccess = {
                    onBackPressed()
                }
            )
    }

    /*서버 연결시 뷰에 뿌려주는 함수 작업.*/
    private fun bindingDetail(detailResponse: DetailResponse) {
        //본격 개막장 함수
        insertUploadUserDataInView(detailResponse.uploader)
        insertImageInViewPager(detailResponse.imageUrl)
        insertKeywordInDetailChip(detailResponse.keyword)
        insertDateTime(detailResponse.placeCreatedAt.toLong())
        val title = detailResponse.placeName
        tv_detail_title.text = title
        tv_detail_top_title.text = title
        tv_detail_content.text = detailResponse.placeReview
        insertCategory(detailResponse.categoryIdx)
        tv_detail_subway_info.text = detailStringForm(detailResponse.subway, "/")
        tv_detail_address_info.text = detailResponse.placeRoadAddress
        tv_detail_place_info.text = detailStringForm(detailResponse.placeInfo, " · ")
        likeCount = detailResponse.likeCount
        tv_detail_shared_people_count.text = ("$likeCount 명")
        tv_detail_bookmark_count.text = detailResponse.bookmarkCount.toString()
        selectViewOrNotDeleteButton(detailResponse.uploader.deleteBtn)
        setMyLikeButtonStatus(detailResponse.isLiked)
        setMyBookmarkButtonStatus(detailResponse.isBookmarked)

        webTitle = title
        webUrl = detailResponse.mobileNaverMapLink
    }

    private fun insertUploadUserDataInView(uploader: Uploader) {
        Glide.with(this).load(uploader.profileImageUrl).into(img_detail_user_profile)
        tv_detail_user_name.text = uploader.userName
        tv_detail_user_part.text = uploader.part
        tv_detail_user_post_count.text = ("작성한 글 ${uploader.postCount}")
    }

    private fun insertImageInViewPager(imageList: List<String>) {
        detailviewPagerAdapter = DetailViewPagerAdapter(this, imageList)
        vp_detail_image_slide.adapter = detailviewPagerAdapter
    }

    private fun insertKeywordInDetailChip(keyword: List<String>) {
        keyword.forEach { text ->
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

    private fun detailStringForm(stringList: List<String>, dividerString: String): String {
        val detailStringBuilder = StringBuilder("")

        stringList.forEach { str ->
            if (str != stringList[0]) {
                detailStringBuilder.append(dividerString)
                detailStringBuilder.append(str)
            } else {
                detailStringBuilder.append(str)
            }
        }
        return detailStringBuilder.toString()
    }

    private fun selectViewOrNotDeleteButton(deleteBtn: Boolean) {
        if (!deleteBtn) {
            tv_btn_detail_top_del.visibility = View.GONE
            return
        }
        tv_btn_detail_top_del.visibility = View.VISIBLE
    }

    private fun likedPage() {
        cl_btn_detail_like.isSelected = true
        img_btn_detail_like.isSelected = true
    }

    private fun notLikedPage() {
        cl_btn_detail_like.isSelected = false
        img_btn_detail_like.isSelected = false
    }

    private fun setLikeButtonClickEvent() {
        if (getMyLikeButtonStatus()) {
            //이미 좋아요
            likeCount -= 1
            tv_detail_shared_people_count.text = ("$likeCount 명")
            requestToDeleteLike()
            return
        }
        //아직 좋아요 안함
        likeCount += 1
        tv_detail_shared_people_count.text = ("$likeCount 명")
        requestToLike()
    }

    private fun setMyLikeButtonStatus(liked: Boolean) {
        if (liked) {
            likedPage()
            return
        }
        notLikedPage()
    }

    private fun getMyLikeButtonStatus(): Boolean {
        return img_btn_detail_like.isSelected && cl_btn_detail_like.isSelected
    }

    private fun bookmarkedPage() {
        img_btn_detail_bookmark.isSelected = true
    }

    private fun notBookmarkedPage() {
        img_btn_detail_bookmark.isSelected = false
    }

    private fun setBookmarkButtonClickEvent() {
        if (getMyBookmarkButtonStatus()) {
            //북마크가 이미 되어있는 상황
            val bookmarkCount = tv_detail_bookmark_count.text.toString().toInt()
            tv_detail_bookmark_count.text = (bookmarkCount - 1).toString()
            requestToDeleteBookmark()
            return
        }
        //북마크가 안되어있을때 이벤트
        val bookmarkCount = tv_detail_bookmark_count.text.toString().toInt()
        tv_detail_bookmark_count.text = (bookmarkCount + 1).toString()
        requestToBookmark()
    }

    private fun setMyBookmarkButtonStatus(bookmarked: Boolean) {
        if (bookmarked) {
            bookmarkedPage()
            return
        }
        notBookmarkedPage()
    }

    private fun getMyBookmarkButtonStatus(): Boolean {
        return img_btn_detail_bookmark.isSelected
    }
}