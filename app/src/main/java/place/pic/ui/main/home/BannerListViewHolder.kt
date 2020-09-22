package place.pic.ui.main.home

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


    fun bind(bannerData: BannerData) {
        //badgeBg.setBackgroundColor(Color.parseColor("#F6CB5C"))
        badge.text = badge.text
        title.text = title.text
        description.text = description.text
        Glide.with(itemView).load(bannerData.imageUrl).into(imageUrl)
    }
}