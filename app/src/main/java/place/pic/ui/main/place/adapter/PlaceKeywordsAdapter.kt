package place.pic.ui.main.place.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.databinding.ItemKeywordTagBinding

/**
 * Created By Malibin
 * on 7월 04, 2020
 */

class PlaceKeywordsAdapter : RecyclerView.Adapter<PlaceKeywordsAdapter.ViewHolder>() {

    private val keywords = mutableListOf<String>()

    override fun getItemCount() = keywords.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemKeywordTagBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keyword = keywords[position]
        holder.bind(keyword)
    }

    fun submitList(keywords: List<String>) {
        clearKeywords()
        this.keywords.addAll(keywords)
        notifyItemRangeInserted(0, itemCount)
    }

    private fun clearKeywords() {
        notifyItemRangeRemoved(0, itemCount)
        this.keywords.clear()
    }

    inner class ViewHolder(
        private val binding: ItemKeywordTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: String) {
            binding.keyword = keyword
        }
    }
}