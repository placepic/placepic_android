package place.pic.ui.main.mypage.mywritings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.data.entity.MyWriting
import place.pic.databinding.ItemMyWritingBinding

/**
 * Created By Malibin
 * on 9월 13, 2020
 */

class MyWritingsAdapter : ListAdapter<MyWriting, MyWritingsAdapter.ViewHolder>(DiffItemCallback()) {

    private var itemClickListener: ((MyWriting) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMyWritingBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myWriting = getItem(position)
        holder.bind(myWriting)
    }

    fun setItemClickListener(itemClickListener: ((MyWriting) -> Unit)?) {
        this.itemClickListener = itemClickListener
    }

    private fun createItemClickListener(myWriting: MyWriting) = View.OnClickListener {
        itemClickListener?.invoke(myWriting)
    }

    inner class ViewHolder(
        private val binding: ItemMyWritingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(myWriting: MyWriting) {
            binding.myWriting = myWriting
            binding.itemClickListener = createItemClickListener(myWriting)
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<MyWriting>() {
        override fun areItemsTheSame(oldItem: MyWriting, newItem: MyWriting): Boolean {
            return oldItem.placeIdx == newItem.placeIdx
        }

        override fun areContentsTheSame(oldItem: MyWriting, newItem: MyWriting): Boolean {
            return oldItem == newItem
        }
    }
}