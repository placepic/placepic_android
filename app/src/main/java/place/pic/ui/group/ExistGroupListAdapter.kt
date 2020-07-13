package place.pic.ui.group

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.data.remote.response.ResponseGroupList

class ExistGroupListAdapter(private var datas: List<ResponseGroupList>, private val context: Context) : RecyclerView.Adapter<ExistGroupListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExistGroupListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_group_list,parent,false)
        return ExistGroupListViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: ExistGroupListViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}