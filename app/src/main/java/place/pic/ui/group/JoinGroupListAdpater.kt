package place.pic.ui.group

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

class JoinGroupListAdapter (
    private val datas:MutableList<ListGroupData>,
    private val context: Context
) : RecyclerView.Adapter<JoinGroupListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoinGroupListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_group_list,parent,false)
        return JoinGroupListViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: JoinGroupListViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}