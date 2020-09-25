package place.pic.ui.main.home.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_banner_list.*
import place.pic.R

class BannerListActivity : AppCompatActivity() {

    lateinit var bannerListAdapter: BannerListAdapter
    val listDatas : MutableList<BannerListData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_list)

        initRcv()
        loadDatas()

        img_banner_list_back.setOnClickListener { onBackPressed() }
    }

    private fun initRcv() {
        bannerListAdapter = BannerListAdapter()
        rv_banner_list.adapter = bannerListAdapter
    }

    private fun loadDatas() {
        listDatas.apply {
            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "제목 1",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#F6CB5C",
                    badge = "PICK",
                    title = "제목 2",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "제목 3",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "제목 4",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#F6CB5C",
                    badge = "PICK",
                    title = "제목 5",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#F6CB5C",
                    badge = "PICK",
                    title = "제목 6",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://odden1.speedgabia.com/static/bb/lists/spot-n03-s02/spot_f_n03-s02-01.jpg"
                )
            )
        }
        bannerListAdapter.listDatas = listDatas
        bannerListAdapter.notifyDataSetChanged()
    }
}