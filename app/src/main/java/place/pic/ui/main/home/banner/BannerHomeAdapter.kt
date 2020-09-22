package place.pic.ui.main.home.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 12일, 2020
 */

class BannerHomeAdapter : RecyclerView.Adapter<BannerHomeViewHolder>() {

    var datas: MutableList<BannerHomeData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHomeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner_home, parent, false)
        return BannerHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerHomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}