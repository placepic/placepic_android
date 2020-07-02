package place.pic.ui.search.subway

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.databinding.ItemSelectedSubwayBinding

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

class SelectedSubwaysAdapter :
    ListAdapter<Subway, SelectedSubwaysAdapter.ViewHolder>(DiffItemCallback()) {

    private var subwaySelectCancelListener: SubwayClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSelectedSubwayBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subway = getItem(position)
        holder.bind(subway)
    }

    fun setSubwaySelectCancelListener(listener: SubwayClickListener) {
        this.subwaySelectCancelListener = listener
    }

    inner class ViewHolder(
        private val binding: ItemSelectedSubwayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subway: Subway) {
            binding.subway = subway
            binding.clickListener = subwaySelectCancelListener
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<Subway>() {
        override fun areItemsTheSame(oldItem: Subway, newItem: Subway): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Subway, newItem: Subway): Boolean {
            return oldItem == newItem
        }
    }
}