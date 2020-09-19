package place.pic.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_banner_list.view.*
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 19일, 2020
 */
 
class BannerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageUrl: ImageView = itemView.findViewById(R.id.img_banner_list)

    fun bind(bannerData: BannerData) {
        Glide.with(itemView).load(bannerData.imageUrl).into(imageUrl)
    }
}