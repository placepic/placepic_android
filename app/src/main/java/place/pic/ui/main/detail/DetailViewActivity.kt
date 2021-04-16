package place.pic.ui.main.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import com.naver.maps.map.* // ktlint-disable no-wildcard-imports
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Place
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.RequestToPlaceIdx
import place.pic.data.remote.response.DetailResponse
import place.pic.data.remote.response.Uploader
import place.pic.databinding.ActivityDetailViewBinding
import place.pic.ui.dialog.SimpleDialog
import place.pic.ui.main.detail.liker.LikerUserListActivity
import place.pic.ui.tag.ChipFactory
import place.pic.ui.util.BindingActivity
import place.pic.ui.util.customEnqueue
import place.pic.ui.util.unixDateTimeParser
import place.pic.ui.webview.InWebActivity

/*
* 글 작성 유저와 글의 장소 ㄹId를 비교하여 글 삭제 버튼의 유무를 지정하기 위해서
* putExtra에 "placeIdx" 키로 placeIdx:Int를 넘겨주시면 됩니다.
*
* 각각의 키로는 Int로 캐스팅하여 사용합니다.
*/

class DetailViewActivity :
    BindingActivity<ActivityDetailViewBinding>(R.layout.activity_detail_view),
    OnMapReadyCallback {
    private lateinit var detailViewPagerAdapter: DetailViewPagerAdapter
    private val detailViewModel: DetailViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                DetailViewModel(PlacepicAuthRepository.getInstance(this@DetailViewActivity)) as T
        }
    }

    private lateinit var detailViewCommentAdapter: DetailViewCommentAdapter

    private lateinit var token: String

    private var placeIdx: Int = 0
    private var userIdx: Int = 0

    private var webUrl: String = ""
    private var webTitle: String = ""

    private var likeCount: Int = 0

    private lateinit var location: LatLng
    private var placeMapFragment: MapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        token = PlacepicAuthRepository.getInstance(this).userToken ?: return
        placeIdx = intent.getIntExtra("placeIdx", 0)
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("n1d1q8lp28")
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this
        init()
        mapSetting()
    }

    private fun setPlaceMapFragment(mapFragment: MapFragment?) {
        this.placeMapFragment = mapFragment
    }

    private fun mapSetting() {
        var mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as MapFragment?
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.map, mapFragment)
                .commit()
        }
        setPlaceMapFragment(mapFragment)
    }

    private fun init() {
        observeCommentInput()
        requestToDetailView()
        buttonEventMapping()
    }

    private fun observeCommentInput() {
        detailViewModel.commentInput.observe(this) {
            detailViewModel.validateComment()
        }
    }

    private fun observeCommentList() {
        detailViewModel.comments.observe(this) {
            detailViewCommentAdapter.addAllComments(it)
        }
    }

    private fun setCommentAdapter() {
        binding.listDetailComment.adapter = detailViewCommentAdapter
        observeCommentList()
    }

    private fun buttonEventMapping() {
        with(binding) {
            imgBtnDetailTopBack.setOnClickListener { onBackPressed() }
            clBtnDetailSharedPeople.setOnClickListener { showLikerUserActivity() }
            clBtnDetailLike.setOnClickListener { setLikeButtonClickEvent() }
            clBtnDetailMoreInfo.setOnClickListener { showMoreDetailInfo() }
            clBtnDetailLike.setOnClickListener { setLikeButtonClickEvent() }
            imgBtnDetailBookmark.setOnClickListener { setBookmarkButtonClickEvent() }
            tvBtnDetailTopDel.setOnClickListener { popDeleteDialog() }
            commentRegisterTextButton.setOnClickListener { applyCommentClickEvent() }
            clDetailUserInfo.setOnClickListener {
                val gotoLikerUserList =
                    Intent(this@DetailViewActivity, LikerUserListActivity::class.java)
                gotoLikerUserList.putExtra("placeIdx", placeIdx)
                startActivity(gotoLikerUserList)
            }
        }
    }

    private fun applyCommentClickEvent() {
        detailViewModel.commentApplyClickEvent(placeIdx) {
            binding.detailContentScroll.smoothScrollTo(
                0,
                binding.detailContentScroll.getChildAt(0).height
            )
        }
        detailViewModel.commentInput.value = ""
    }

    private fun showLikerUserActivity() {
        val gotoLikerUserList = Intent(this, LikerUserListActivity::class.java)
        gotoLikerUserList.putExtra("placeIdx", placeIdx)
        startActivity(gotoLikerUserList)
    }

    private fun popDeleteDialog() {
        // TODO 글 삭제시 PlaceList 수정이 필요함.
        SimpleDialog(this).apply {
            setContent(R.string.are_you_sure)
            setCancelClickListener(R.string.close) { dismiss() }
            setOkClickListener(R.string.delete) { dismiss(); requestToDeletePlace() }
        }.show()
    }

    private fun showMoreDetailInfo() {
        val gotoWebViewIntent = Intent(this, InWebActivity::class.java)
        gotoWebViewIntent.putExtra("webUrl", webUrl)
        gotoWebViewIntent.putExtra("webTitle", webTitle)
        startActivity(gotoWebViewIntent)
    }

    override fun onBackPressed() {
        if (!getMyBookmarkButtonStatus()) {
            val intent = Intent().apply { putExtra("placeIdx", placeIdx) }
            setResult(2, intent)
        }
        super.onBackPressed()
    }

    /* 서버 연결 */
    // 리스트 불러오기
    private fun requestToDetailView() {
        PlacePicService.getInstance()
            .requestDetail(
                token = token,
                placeIdx = placeIdx
            ).customEnqueue(
                onSuccess = { response ->
                    val serverData = response.body()?.data ?: return@customEnqueue
                    bindingDetail(serverData)
                    placeMapFragment!!.getMapAsync(this)
                }
            )
    }

    private fun requestToComments() {
        detailViewModel.loadComments(placeIdx)
    }

    private fun requestToUserInfo() {
        detailViewModel.loadUserInfo()
        detailViewCommentAdapter = DetailViewCommentAdapter()
        detailViewCommentAdapter.setPopUpCommentDelEvent { commentIdx ->
            Log.d("test", "댓글 삭제")
            SimpleDialog(this).apply {
                setContent("정말로 이 댓글을 삭제할까요?")
                setCancelClickListener(R.string.close) { dismiss() }
                setOkClickListener(R.string.delete) { dismiss(); requestToDelComment(commentIdx) }
            }.show()
        }
        setCommentAdapter()
    }

    private fun requestToDelComment(commentIdx: Int) {
        detailViewModel.commentDeleteClickEvent(placeIdx, commentIdx)
    }

    // 좋아요 관련 서버 연결
    private fun requestToLike() {
        PlacePicService.getInstance()
            .requestToLike(
                token = token,
                body = RequestToPlaceIdx(placeIdx)
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

    // 북마크 서버연결
    private fun requestToBookmark() {
        PlacePicService.getInstance()
            .requestToBookmark(
                token = token,
                body = RequestToPlaceIdx(placeIdx)
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

    // 글 삭제 서버 연결
    private fun requestToDeletePlace() {
        PlacePicService.getInstance()
            .requestToDeletePlace(
                token = token,
                placeIdx = placeIdx
            ).customEnqueue(
                onSuccess = {
                    val intent = Intent().apply { putExtra("placeIdx", placeIdx) }
                    setResult(1, intent)
                    finish()
                }
            )
    }

    /*서버 연결시 뷰에 뿌려주는 함수 작업.*/
    @RequiresApi(Build.VERSION_CODES.N)
    private fun bindingDetail(detailResponse: DetailResponse) {
        // 본격 개막장 함수
        insertUploadUserDataInView(detailResponse.uploader)
        insertImageInViewPager(detailResponse.imageUrl)
        insertKeywordInDetailChip(detailResponse.keyword)
        insertDateTime(detailResponse.placeCreatedAt.toLong())
        val title = detailResponse.placeName
        insertCategory(detailResponse.categoryIdx)
        likeCount = detailResponse.likeCount
        selectViewOrNotDeleteButton(detailResponse.uploader.deleteBtn)
        setMyLikeButtonStatus(detailResponse.isLiked)
        setMyBookmarkButtonStatus(detailResponse.isBookmarked)
        with(binding) {
            tvDetailTitle.text = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY).toString()
            tvDetailTopTitle.text = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY).toString()
            tvDetailContent.text = detailResponse.placeReview
            tvDetailSubwayInfo.text = detailStringForm(detailResponse.subway, "/")
            tvDetailAddressInfo.text = detailResponse.placeRoadAddress
            tvDetailPlaceInfo.text = detailStringForm(detailResponse.placeInfo, " · ")
            tvDetailSharedPeopleCount.text = ("$likeCount 명")
            tvDetailBookmarkCount.text = detailResponse.bookmarkCount.toString()
        }
        userIdx = detailResponse.uploader.userIdx
        location = setLatLng(detailResponse.placeMapX, detailResponse.placeMapY)
        requestToUserInfo()
        requestToComments()
        webTitle = title
        webUrl = detailResponse.mobileNaverMapLink
    }

    private fun insertUploadUserDataInView(uploader: Uploader) {
        with(binding) {
            Glide.with(this@DetailViewActivity)
                .load(uploader.profileImageUrl)
                .into(imgDetailUserProfile)
            tvDetailUserName.text = uploader.userName
            tvDetailUserPart.text = uploader.part
            tvDetailUserPostCount.text = ("작성한 글 ${uploader.postCount}")
        }
    }

    private fun insertImageInViewPager(imageList: List<String>) {
        detailViewPagerAdapter = DetailViewPagerAdapter(this, imageList)
        with(binding) {
            vpDetailImageSlide.adapter = detailViewPagerAdapter
            circleIndicator.setupWithViewPager(vpDetailImageSlide)
        }
    }

    private fun insertKeywordInDetailChip(keyword: List<String>) {
        keyword.forEach { text ->
            val chip = ChipFactory.createDetailChip(layoutInflater)
            chip.text = text
            binding.chipGroupUsefulInfo.addView(chip)
        }
    }

    private fun insertDateTime(placeCreatedAt: Long) {
        binding.tvDetailUserCreateAt.text = unixDateTimeParser(placeCreatedAt)
    }

    private fun insertCategory(categoryIdx: Int) {
        val category = Place.Type.findByPosition(categoryIdx).value
        binding.tvDetailCategoryInfo.text = category
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
            binding.tvBtnDetailTopDel.visibility = View.GONE
            return
        }
        binding.tvBtnDetailTopDel.visibility = View.VISIBLE
    }

    private fun likedPage() {
        with(binding) {
            clBtnDetailLike.isSelected = true
            imgBtnDetailLike.isSelected = true
        }
    }

    private fun notLikedPage() {
        with(binding) {
            clBtnDetailLike.isSelected = false
            imgBtnDetailLike.isSelected = false
        }
    }

    private fun setLikeButtonClickEvent() {
        if (getMyLikeButtonStatus()) {
            // 이미 좋아요
            likeCount -= 1
            binding.tvDetailSharedPeopleCount.text = ("$likeCount 명")
            requestToDeleteLike()
            return
        }
        // 아직 좋아요 안함
        likeCount += 1
        binding.tvDetailSharedPeopleCount.text = ("$likeCount 명")
        requestToLike()
    }

    private fun setMyLikeButtonStatus(liked: Boolean) {
        if (liked) {
            likedPage()
            return
        }
        notLikedPage()
    }

    private fun getMyLikeButtonStatus(): Boolean = with(binding) {
        imgBtnDetailLike.isSelected && clBtnDetailLike.isSelected
    }

    private fun bookmarkedPage() {
        binding.imgBtnDetailBookmark.isSelected = true
    }

    private fun notBookmarkedPage() {
        binding.imgBtnDetailBookmark.isSelected = false
    }

    private fun setBookmarkButtonClickEvent() {
        if (getMyBookmarkButtonStatus()) {
            // 북마크가 이미 되어있는 상황
            val bookmarkCount = binding.tvDetailBookmarkCount.text.toString().toInt()
            binding.tvDetailBookmarkCount.text = (bookmarkCount - 1).toString()
            requestToDeleteBookmark()
            return
        }
        // 북마크가 안되어있을때 이벤트
        val bookmarkCount = binding.tvDetailBookmarkCount.text.toString().toInt()
        binding.tvDetailBookmarkCount.text = (bookmarkCount + 1).toString()
        requestToBookmark()
    }

    private fun setMyBookmarkButtonStatus(bookmarked: Boolean) {
        if (bookmarked) {
            bookmarkedPage()
            return
        }
        notBookmarkedPage()
    }

    private fun getMyBookmarkButtonStatus(): Boolean = binding.imgBtnDetailBookmark.isSelected

    override fun onMapReady(naverMap: NaverMap) {
        naverMap.minZoom = 10.0; // 최소
        naverMap.maxZoom = 19.0
        naverMap.cameraPosition = CameraPosition(location, 21.0)
        val marker = Marker()
        marker.position = location
        marker.icon = OverlayImage.fromResource(R.drawable.ic_pink_pin)
        marker.map = naverMap
    }

    private fun setLatLng(placeMapX: Double, placeMapY: Double): LatLng {
        val tm128 = Tm128(placeMapX, placeMapY)
        return tm128.toLatLng()
    }
}
