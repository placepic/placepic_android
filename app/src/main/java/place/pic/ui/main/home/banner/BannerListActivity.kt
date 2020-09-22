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
                    badge = "PICK",
                    title = "이게 뭘까",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "지금 매우 당황",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )

            add(
                BannerListData(
                    badgeBg = "#5BC9A1",
                    badge = "공모전",
                    title = "분위기 좋은 카페",
                    description = "내 친구들의 최애장소 24곳",
                    imageUrl = "https://lh3.googleusercontent.com/proxy/NiEg3Nab3esZ1MywwqRkupJu7dpwU2TK_-WVtFghd2OZhD5xDJd1Un9Z2HjRE7e0MzEWte498dzm7vaIDmgAQhHo0sgTVVxCtDkNFYal97XlJHXm6AAck-bp1R1v8ZYD-dS8ZRA"
                )
            )
        }
        bannerListAdapter.listDatas = listDatas
        bannerListAdapter.notifyDataSetChanged()
    }
}