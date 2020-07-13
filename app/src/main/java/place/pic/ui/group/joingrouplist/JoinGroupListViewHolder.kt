package place.pic.ui.group.joingrouplist

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.group.SignUpGroupActivity

class JoinGroupListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val img_group_profile = itemView.findViewById<ImageView>(R.id.img_group_profile)
    val tv_group_title = itemView.findViewById<TextView>(R.id.tv_group_title)
    val tv_group = itemView.findViewById<TextView>(R.id.tv_group_count)

    fun bind(customData: ResponseGroupList) {
        tv_group_title.text = customData.groupName
        tv_group.text = stringDataMapping(customData)
        Glide.with(itemView).load(customData.groupImage).into(img_group_profile)
        itemViewClickEvent(customData)
    }

    private fun stringDataMapping(customData: ResponseGroupList):String{
        return itemView.context
            .getString(
                R.string.group_count,
                customData.UserCount,
                customData.PostCount
            )
    }

    private fun itemViewClickEvent(customData: ResponseGroupList) {
        itemView.setOnClickListener {
            Toast.makeText(
                itemView.context,
                "DetailClick",
                Toast.LENGTH_SHORT
            ).show()
            val gotoSignUpIntent = Intent(itemView.context,
                SignUpGroupActivity::class.java)
            gotoSignUpIntent.putExtra("groupIdx",customData.groupIdx)
            itemView.context
                .startActivity(gotoSignUpIntent)
        }
    }
}