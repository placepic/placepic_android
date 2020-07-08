package place.pic.ui.search.subway.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.databinding.ItemSubwayLineBinding
import place.pic.data.entity.Subway

/**
 * Created By Malibin
 * on 7월 02, 2020
 */

class LinesAdapter : RecyclerView.Adapter<LinesAdapter.ViewHolder>() {

    private val lines = mutableListOf<Subway.Line>()

    override fun getItemCount(): Int = lines.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSubwayLineBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val line = lines[position]
        holder.bind(line)
    }

    fun submitList(items: List<Subway.Line>) {
        clearList()
        lines.addAll(items)
        notifyItemRangeInserted(0, itemCount)
    }

    private fun clearList() {
        notifyItemRangeRemoved(0, itemCount)
        lines.clear()
    }

    inner class ViewHolder(
        private val binding: ItemSubwayLineBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(line: Subway.Line) {
            binding.line = line
        }
    }
}

