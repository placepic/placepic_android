package place.pic

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter(
    private var datas: ArrayList<Uri>,
    private val context: Context
) : RecyclerView.Adapter<PhotoViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount() : Int {
        return datas.size
    }
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.bt_photo_delete.setOnClickListener {
            Log.d("imageDel", "del")
            datas.remove(datas[position])
            if (PostplaceActivity().selectedImage.size > 0) {
                PostplaceActivity().selectedImage.remove(datas[position])
            }
            notifyDataSetChanged()
        }
    }
}