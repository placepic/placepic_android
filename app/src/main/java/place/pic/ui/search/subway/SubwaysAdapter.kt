package place.pic.ui.search.subway

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.databinding.ItemSearchedSubwayBinding

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

class SubwaysAdapter : ListAdapter<Subway, SubwaysAdapter.ViewHolder>(DiffItemCallback()) {

    private var subwayClickListener: ((subway: Subway) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchedSubwayBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subway = getItem(position)
        holder.bind(subway)
    }

    fun setSubwayClickListener(listener: ((subway: Subway) -> Unit)?) {
        this.subwayClickListener = listener
    }

    private fun createClickListener(subway: Subway) = View.OnClickListener {
        subwayClickListener?.invoke(subway)
    }

    inner class ViewHolder(
        private val binding: ItemSearchedSubwayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subway: Subway) {
            binding.subway = subway
            binding.itemClickListener = createClickListener(subway)
            binding.rvSubwayLines.adapter = LinesAdapter().apply { submitList(subway.line) }
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