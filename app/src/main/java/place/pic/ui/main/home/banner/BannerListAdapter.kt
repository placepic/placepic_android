package place.pic.ui.main.home.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 19일, 2020
 */
 
class BannerListAdapter  : RecyclerView.Adapter<BannerListViewHolder>() {

    var listDatas: MutableList<BannerListData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner_list, parent, false)
        return BannerListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerListViewHolder, position: Int) {
        holder.bind(listDatas[position])
    }

    override fun getItemCount(): Int {
        return listDatas.size
    }

}