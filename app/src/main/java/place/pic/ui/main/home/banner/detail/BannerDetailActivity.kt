package place.pic.ui.main.home.banner.detail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Banner
import place.pic.data.entity.PlaceGridItem
import place.pic.databinding.ActivityBannerDetailBinding
import place.pic.ui.main.detail.DetailViewActivity
import place.pic.ui.main.place.adapter.PlaceGridItemsAdapter
import place.pic.ui.util.loadImageFrom

class BannerDetailActivity : AppCompatActivity() {

    private val placeGridItemsAdapter by lazy { PlaceGridItemsAdapter() }
    private val bannerDetailViewModel by lazy {
        BannerDetailViewModel(PlacepicAuthRepository.getInstance(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        subscribePlaces()
        subscribeBanner()
        bannerDetailViewModel.requestBannerDetail(getBannerId())
    }

    private fun initView() {
        val binding = ActivityBannerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placeGridItemsAdapter.setItemClickListener { deployPlaceDetail(it) }
        binding.lifecycleOwner = this
        binding.rvPlaceGridItems.adapter = placeGridItemsAdapter
        binding.btnBack.setOnClickListener { onBackPressed() }
    }

    private fun deployPlaceDetail(placeItem: PlaceGridItem) {
        val intent = Intent(this, DetailViewActivity::class.java)
        intent.putExtra("placeIdx", placeItem.placeIdx)
        startActivityForResult(intent, 5000)
    }

    private fun subscribePlaces() {
        bannerDetailViewModel.places.observe(this) {
            placeGridItemsAdapter.submitList(it)
        }
    }

    private fun subscribeBanner() {
        bannerDetailViewModel.banner.observe(this) {
            updateBanner(it)
        }
    }

    private fun updateBanner(banner: Banner) {
        findViewById<View>(R.id.c_banner_badge_bg).background.setTint(Color.parseColor(banner.badgeColorCode))
        findViewById<TextView>(R.id.tv_banner_title).text = banner.title
        findViewById<TextView>(R.id.tv_banner_desc).text = banner.description
        findViewById<TextView>(R.id.tv_banner_badge).text = banner.badgeName
        findViewById<ImageView>(R.id.img_banner_list).loadImageFrom(banner.imageUrl)
    }

    private fun getBannerId(): Int = intent.getIntExtra("bannerId", -1)
}