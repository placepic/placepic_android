package place.pic.ui.group

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

class ExistGroupListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val img_group_profile = itemView.findViewById<ImageView>(R.id.img_group_profile)
    val tv_group_title = itemView.findViewById<TextView>(R.id.tv_group_title)
    val tv_group = itemView.findViewById<TextView>(R.id.tv_group_count)


    fun bind(customData: ListGroupData) {

        val groupCount =
            itemView.context
                .getString(
                    R.string.group_count,
                    customData.people_count,
                    customData.write_count
                )
    }
}
