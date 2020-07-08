package place.pic.ui.group

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

class WaitGroupAdapter (
    private val datas:MutableList<WaitListGroupData>,
    private val context: Context
) : RecyclerView.Adapter<WaitGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitGroupViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_group_list,parent,false)
        return WaitGroupViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: WaitGroupViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}