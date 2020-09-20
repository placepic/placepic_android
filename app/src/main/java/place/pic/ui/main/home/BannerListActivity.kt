package place.pic.ui.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_banner_list.*
import place.pic.R

class BannerListActivity : AppCompatActivity() {

    lateinit var bannerListAdapter: BannerListAdapter
    val datas : MutableList<BannerData> = mutableListOf()

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
        datas.apply {
            add(
                BannerData(
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtEpw2BhEgmIHtYkPY1NEiE4mNo8V6hwCbLw&usqp=CAU"
            ))

            add(
                BannerData(
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtEpw2BhEgmIHtYkPY1NEiE4mNo8V6hwCbLw&usqp=CAU"
                ))

            add(
                BannerData(
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtEpw2BhEgmIHtYkPY1NEiE4mNo8V6hwCbLw&usqp=CAU"
                ))

            add(
                BannerData(
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtEpw2BhEgmIHtYkPY1NEiE4mNo8V6hwCbLw&usqp=CAU"
                ))

            add(
                BannerData(
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtEpw2BhEgmIHtYkPY1NEiE4mNo8V6hwCbLw&usqp=CAU"
                ))

            add(
                BannerData(
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtEpw2BhEgmIHtYkPY1NEiE4mNo8V6hwCbLw&usqp=CAU"
                ))

            add(
                BannerData(
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtEpw2BhEgmIHtYkPY1NEiE4mNo8V6hwCbLw&usqp=CAU"
                ))
        }
        bannerListAdapter.datas = datas
        bannerListAdapter.notifyDataSetChanged()
    }
}