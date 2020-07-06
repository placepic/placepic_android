package place.pic

import android.media.Image
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_postplace.view.*

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img_contents:ImageView = itemView.findViewById<ImageView>(R.id.img_contents)
    val bt_photo_delete:ImageView = itemView.findViewById<ImageView>(R.id.bt_photo_delete)

    fun bind(customData: Uri) {
        Log.d("test",customData.toString())
        img_contents.setImageURI(customData)
        bt_photo_delete.setOnClickListener {
            Log.d("imageDel","del")
            PostplaceActivity().selectedImage.remove(customData)
            itemView.rv_photo_list.adapter!!.notifyDataSetChanged()
        }
    }
}