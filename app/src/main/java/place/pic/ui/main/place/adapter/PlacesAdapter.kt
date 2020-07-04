package place.pic.ui.main.place.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.R
import place.pic.databinding.ItemPlaceBinding
import place.pic.databinding.ItemPlaceFilterBinding
import place.pic.ui.main.place.Place

/**
 * Created By Malibin
 * on 7월 03, 2020
 */

class PlacesAdapter : ListAdapter<Place, RecyclerView.ViewHolder>(DiffItemCallback()) {

    override fun getItemViewType(position: Int) = when (position) {
        0 -> R.layout.item_place_filter
        else -> R.layout.item_place
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_place_filter -> createHeaderViewHolder(layoutInflater, parent)
            R.layout.item_place -> createItemViewHolder(layoutInflater, parent)
            else -> throw IllegalArgumentException("ViewType [$viewType] is unexpected")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val place = getItem(position)
            holder.bind(place)
        }
    }

    override fun submitList(list: List<Place>?) {
        val newList = list?.toMutableList()?.apply {
            add(
                0,
                Place.empty()
            )
        }
        super.submitList(newList)
    }

    private fun createHeaderViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HeaderViewHolder {
        val binding = ItemPlaceFilterBinding.inflate(inflater, parent, false)
        return HeaderViewHolder(binding)
    }

    private fun createItemViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemViewHolder {
        val binding = ItemPlaceBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    inner class HeaderViewHolder(
        private val binding: ItemPlaceFilterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {

        }
    }

    inner class ItemViewHolder(
        private val binding: ItemPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            val adapter = PlaceKeywordsAdapter()
            binding.rvKeywords.adapter = adapter
            adapter.submitList(place.keywordTags)
            binding.place = place
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }
}