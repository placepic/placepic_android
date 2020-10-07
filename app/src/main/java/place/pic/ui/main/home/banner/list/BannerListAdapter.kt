package place.pic.ui.main.home.banner.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.ui.search.place.PlaceSearchAdapter

/**
 * Created By kimdahyee
 * on 09월 19일, 2020
 */
 
class BannerListAdapter  : RecyclerView.Adapter<BannerListViewHolder>() {

    var datas: MutableList<BannerListData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner_list, parent, false)
        return BannerListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerListViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    //click interface
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}