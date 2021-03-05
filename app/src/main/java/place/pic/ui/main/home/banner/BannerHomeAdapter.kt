package place.pic.ui.main.home.banner

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 12일, 2020
 */

class BannerHomeAdapter() : RecyclerView.Adapter<BannerHomeViewHolder>() {

    var datas: MutableList<BannerHomeData> = mutableListOf()

    //getItemViewType 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHomeViewHolder { //3
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner_home, parent, false)
        return BannerHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerHomeViewHolder, position: Int) { //4
        val realPos = position % datas.size
        val stringBuilder = StringBuilder("")
        stringBuilder.append((realPos + 1).toString())
        stringBuilder.append(" / ")
        stringBuilder.append(datas.size)
        holder.bind(datas[realPos], stringBuilder.toString())

        holder.itemView.setOnClickListener{
            if (datas[0].bannerIdx == -1) {
                itemClickListener.onItemClick(it, -1)
            } else {
                itemClickListener.onItemClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int { //1
        return if (datas.size == 1) {
            datas.size
        } else {
            datas.size * 1000
        }
        //return Integer.MAX_VALUE
    }

    //click interface
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}