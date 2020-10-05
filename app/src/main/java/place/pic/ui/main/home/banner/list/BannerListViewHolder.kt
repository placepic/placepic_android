package place.pic.ui.main.home.banner.list

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 19일, 2020
 */
 
class BannerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val badgeBg: View = itemView.findViewById(R.id.c_banner_badge_bg)
    val badge: TextView = itemView.findViewById(R.id.tv_banner_badge)
    val title: TextView = itemView.findViewById(R.id.tv_banner_title)
    val description: TextView = itemView.findViewById(R.id.tv_banner_desc)
    val imageUrl: ImageView = itemView.findViewById(R.id.img_banner_list)

    fun bind(bannerListData: BannerListData) {
        badgeBg.background.setTint(Color.parseColor(bannerListData.badgeBg))
        badge.text = bannerListData.badge
        title.text = bannerListData.title
        description.text = bannerListData.description

        if (bannerListData.description == "") {
            description.visibility = View.GONE
        } else {
            description.visibility = View.VISIBLE
            description.text = bannerListData.description
        }

        Glide.with(itemView).load(bannerListData.imageUrl).into(imageUrl)
    }
}