package place.pic.ui.main.home.banner

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.ui.main.home.banner.BannerHomeData

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

    fun bind(bannerHomeData: BannerHomeData) {
        //badgeBg.setBackgroundColor(Color.parseColor("#F6CB5C"))
        badge.text = badge.text
        title.text = title.text
        description.text = description.text
        Glide.with(itemView).load(bannerHomeData.imageUrl).into(imageUrl)
        count.text = count.text
    }
}