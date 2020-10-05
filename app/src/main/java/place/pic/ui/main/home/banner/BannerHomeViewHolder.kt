package place.pic.ui.main.home.banner

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 22일, 2020
 */
 
class BannerHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val badgeBg: View = itemView.findViewById(R.id.c_banner_badge_bg_home)
    val badge: TextView = itemView.findViewById(R.id.tv_banner_badge_home)
    val title: TextView = itemView.findViewById(R.id.tv_banner_title_home)
    val description: TextView = itemView.findViewById(R.id.tv_banner_desc_home)
    val imageUrl: ImageView = itemView.findViewById(R.id.img_banner_home)
    val count: TextView = itemView.findViewById(R.id.tv_banner_count_home)

    fun bind(bannerHomeData: BannerHomeData, position: String) {
        Glide.with(itemView).load(bannerHomeData.imageUrl).into(imageUrl)
        badgeBg.background.setTint(Color.parseColor(bannerHomeData.badgeBg))
        badge.text = bannerHomeData.badge
        title.text = bannerHomeData.title

        if (bannerHomeData.description == "") {
            description.visibility = View.GONE
        } else {
            description.visibility = View.VISIBLE
            description.text = bannerHomeData.description
        }

        count.text = position
    }
}
