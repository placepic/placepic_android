package place.pic.ui.upload.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.data.entity.Subway
import place.pic.databinding.ItemSubwayBinding

/**
 * Created By Malibin
 * on 7월 07, 2020
 */

class SubwaysAdapter : ListAdapter<Subway, SubwaysAdapter.ViewHolder>(DiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSubwayBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subway = getItem(position)
        holder.bind(subway)
    }

    inner class ViewHolder(
        private val binding: ItemSubwayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subway: Subway) {
            binding.subway = subway
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<Subway>() {
        override fun areItemsTheSame(oldItem: Subway, newItem: Subway): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Subway, newItem: Subway): Boolean {
            return oldItem == newItem
        }
    }
}