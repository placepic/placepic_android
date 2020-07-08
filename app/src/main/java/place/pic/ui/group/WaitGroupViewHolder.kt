package place.pic.ui.group

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R

class WaitGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val img_group_profile = itemView.findViewById<ImageView>(R.id.img_group_profile)
    val tv_group_title = itemView.findViewById<TextView>(R.id.tv_group_title)
    val tv_group = itemView.findViewById<TextView>(R.id.tv_group_count)
    val img_btn_goto_group = itemView.findViewById<ImageView>(R.id.img_btn_goto_group)

    fun bind(customData: WaitListGroupData) {
        img_btn_goto_group.visibility = View.INVISIBLE
        tv_group_title.text = customData.title
        tv_group.text = stringDataMapping(customData)
        Glide.with(itemView).load(customData.url).into(img_group_profile)
    }

    private fun stringDataMapping(customData: WaitListGroupData):String{
        return itemView.context
            .getString(
                R.string.group_count,
                customData.people_count,
                customData.write_count
            )
    }
}