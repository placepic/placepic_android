package place.pic.ui.upload

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.R
import place.pic.databinding.*

/**
 * Created By Malibin
 * on 7월 07, 2020
 */

class ImagesToUploadAdapter : ListAdapter<ImageUri, RecyclerView.ViewHolder>(DiffItemCallback()) {

    private var imageDeleteListener: ((imageUri: ImageUri) -> Unit)? = null
    private var getImageButtonListener: ((view: View) -> Unit)? = null

    override fun getItemViewType(position: Int) = when (position) {
        0 -> R.layout.item_get_image_button
        else -> R.layout.item_image_to_upload
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_get_image_button ->
                createGetImageButtonViewHolder(layoutInflater, parent)
            R.layout.item_image_to_upload ->
                createImageViewHolder(layoutInflater, parent)
            else -> throw IllegalArgumentException("ViewType [$viewType] is unexpected")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            val image = getItem(position)
            holder.bind(image)
        }
    }

    override fun submitList(list: List<ImageUri>?) {
        val newList = list?.toMutableList()?.apply {
            add(0, ImageUri(Uri.EMPTY))
        }
        super.submitList(newList)
    }

    private fun createGetImageButtonViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): GetImageButtonViewHolder {
        val binding = ItemGetImageButtonBinding.inflate(inflater, parent, false)
        return GetImageButtonViewHolder(binding).apply { bind() }
    }

    private fun createImageViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ImageViewHolder {
        val binding = ItemImageToUploadBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    private fun createImageDeleteListener(imageUri: ImageUri) = View.OnClickListener {
        this.imageDeleteListener?.invoke(imageUri)
    }

    fun setOnImageDeleteListener(listener: ((imageUri: ImageUri) -> Unit)?) {
        this.imageDeleteListener = listener
    }

    fun setGettingImageButtonListener(listener: ((view: View) -> Unit)?) {
        this.getImageButtonListener = listener
    }

    inner class ImageViewHolder(
        private val binding: ItemImageToUploadBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: ImageUri) {
            binding.imageDeleteListener = createImageDeleteListener(image)
            Glide.with(binding.ivImage)
                .load(image.uri)
                .into(binding.ivImage)
        }
    }

    inner class GetImageButtonViewHolder(
        private val binding: ItemGetImageButtonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.btnGetImages.setOnClickListener(getImageButtonListener)
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<ImageUri>() {
        override fun areItemsTheSame(oldItem: ImageUri, newItem: ImageUri): Boolean {
            return oldItem.uri == newItem.uri
        }

        override fun areContentsTheSame(oldItem: ImageUri, newItem: ImageUri): Boolean {
            return oldItem == newItem
        }
    }
}