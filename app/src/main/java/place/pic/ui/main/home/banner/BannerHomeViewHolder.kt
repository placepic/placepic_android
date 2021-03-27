package place.pic.ui.main.home.banner

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 22일, 2020
 */
 
class BannerHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val badgeBg: View = itemView.findViewById(R.id.c_banner_badge_bg_home)
    private val badge: TextView = itemView.findViewById(R.id.tv_banner_badge_home)
    val title: TextView = itemView.findViewById(R.id.tv_banner_title_home)
    private val description: TextView = itemView.findViewById(R.id.tv_banner_desc_home)
    val imageUrl: ImageView = itemView.findViewById(R.id.img_banner_home)
    val count: TextView = itemView.findViewById(R.id.tv_banner_count_home)

    private val constraintEmptyBanner: ConstraintLayout = itemView.findViewById(R.id.constraint_empty_banner)

    fun bind(bannerHomeData: BannerHomeData, position: String) {
        if (bannerHomeData.bannerIdx == -1) {
            constraintEmptyBanner.visibility = View.VISIBLE
        } else {
            constraintEmptyBanner.visibility = View.GONE
            
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
}
