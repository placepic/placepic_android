package place.pic.ui.main.home.banner.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import place.pic.R
import place.pic.data.entity.PlaceGridItem
import place.pic.databinding.ActivityBannerDetailBinding
import place.pic.ui.main.detail.DetailViewActivity
import place.pic.ui.main.place.adapter.PlaceGridItemsAdapter
import place.pic.ui.main.place.items.PlaceGridItemClickListener

class BannerDetailActivity : AppCompatActivity() {

    private val placeGridItemsAdapter by lazy { PlaceGridItemsAdapter() }
    private val bannerDetailViewModel by lazy { BannerDetailViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        subscribePlaces()
    }

    private fun initView() {
        val binding = ActivityBannerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        placeGridItemsAdapter.setItemClickListener { deployPlaceDetail(it) }
        binding.lifecycleOwner = this
        binding.rvPlaceGridItems.adapter = placeGridItemsAdapter
        binding.btnBack.setOnClickListener { onBackPressed() }
    }

    private fun initBanner() {
        findViewById<TextView>(R.id.tv_banner_title).text = "제목"
        findViewById<TextView>(R.id.tv_banner_desc).text = "소제목"
        findViewById<TextView>(R.id.tv_banner_badge).text = "뱃지"
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
}