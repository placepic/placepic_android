package place.pic.ui.main.place.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.data.entity.PlaceGridItem
import place.pic.databinding.GridItemPlaceBinding
import place.pic.ui.main.place.items.PlaceGridItemClickListener

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

class PlaceGridItemsAdapter :
    ListAdapter<PlaceGridItem, PlaceGridItemsAdapter.ViewHolder>(DiffItemCallback()) {

    private var itemClickListener: PlaceGridItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GridItemPlaceBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val placeGridItem = getItem(position)
        holder.bind(placeGridItem)
    }

    fun setItemClickListener(itemClickListener: PlaceGridItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(
        private val binding: GridItemPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(placeGridItem: PlaceGridItem) {
            binding.item = placeGridItem
            binding.clickListener = itemClickListener
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<PlaceGridItem>() {
        override fun areItemsTheSame(oldItem: PlaceGridItem, newItem: PlaceGridItem): Boolean {
            return oldItem.placeIdx == newItem.placeIdx
        }

        override fun areContentsTheSame(oldItem: PlaceGridItem, newItem: PlaceGridItem): Boolean {
            return oldItem == newItem
        }
    }
}