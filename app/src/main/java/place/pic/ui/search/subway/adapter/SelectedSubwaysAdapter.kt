package place.pic.ui.search.subway.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.databinding.ItemSelectedSubwayBinding
import place.pic.data.entity.Subway

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

class SelectedSubwaysAdapter :
    ListAdapter<Subway, SelectedSubwaysAdapter.ViewHolder>(DiffItemCallback()) {

    private var subwaySelectCancelListener: ((subway: Subway) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSelectedSubwayBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subway = getItem(position)
        holder.bind(subway)
    }

    fun setSubwaySelectCancelListener(listener: ((subway: Subway) -> Unit)?) {
        this.subwaySelectCancelListener = listener
    }

    private fun createClickListener(subway: Subway) = View.OnClickListener {
        subwaySelectCancelListener?.invoke(subway)
    }

    inner class ViewHolder(
        private val binding: ItemSelectedSubwayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subway: Subway) {
            binding.subway = subway
            binding.clickListener = createClickListener(subway)
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