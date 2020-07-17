package place.pic.ui.main.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.data.entity.Bookmark
import place.pic.databinding.ItemBookmarkPlaceBinding

/**
 * Created By Malibin
 * on 7월 17, 2020
 */

class BookmarksAdapter : ListAdapter<Bookmark, BookmarksAdapter.ViewHolder>(DiffItemCallback()) {

    private var itemClickListener: ((bookmark: Bookmark) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookmarkPlaceBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookmark = getItem(position)
        holder.bind(bookmark)
    }

    fun setItemClickListener(itemClickListener: ((bookmark: Bookmark) -> Unit)?) {
        this.itemClickListener = itemClickListener
    }

    private fun createItemClickListener(bookmark: Bookmark) = View.OnClickListener {
        itemClickListener?.invoke(bookmark)
    }

    inner class ViewHolder(
        private val binding: ItemBookmarkPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bookmark: Bookmark) {
            binding.bookmark = bookmark
            binding.itemClickListener = createItemClickListener(bookmark)
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<Bookmark>() {
        override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem.placeIdx == newItem.placeIdx
        }

        override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem == newItem
        }
    }
}