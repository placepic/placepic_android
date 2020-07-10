package place.pic.ui.group.joinGroupList

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.ui.group.ListGroupData
import place.pic.ui.group.SignUpGroupActivity

class JoinGroupListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val img_group_profile = itemView.findViewById<ImageView>(R.id.img_group_profile)
    val tv_group_title = itemView.findViewById<TextView>(R.id.tv_group_title)
    val tv_group = itemView.findViewById<TextView>(R.id.tv_group_count)

    fun bind(customData: ListGroupData) {
        tv_group_title.text = customData.title
        tv_group.text = stringDataMapping(customData)
        Glide.with(itemView).load(customData.url).into(img_group_profile)
        itemViewClickEvent()
    }

    private fun stringDataMapping(customData: ListGroupData):String{
        return itemView.context
            .getString(
                R.string.group_count,
                customData.people_count,
                customData.write_count
            )
    }

    private fun itemViewClickEvent(){
        itemView.setOnClickListener {
            Toast.makeText(
                itemView.context,
                "DetailClick",
                Toast.LENGTH_SHORT
            ).show()
            val gotoSignUpIntent = Intent(itemView.context,
                SignUpGroupActivity::class.java)
            itemView.context
                .startActivity(gotoSignUpIntent)
        }
    }
}