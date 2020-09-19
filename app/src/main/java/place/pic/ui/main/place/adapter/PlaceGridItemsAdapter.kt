package place.pic.ui.main.place.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.data.entity.PlaceGridItem
import place.pic.databinding.GridItemPlaceBinding
import place.pic.ui.main.place.items.PlaceGridItemClickListener

/**
 * Created By Malibin
 * on 9월 13, 2020
 *
 * 그냥 이 객체를 만들고 RecyclerView Adapter에 집어넣으세요. 그게 끝입니다.
 * 1. 객체만들기
 * 2. 클릭 리스터 달기
 * 3. 객체를 RecyclerView Adapter에 집어넣기
 *
 * submitList() 호출 시 입력 한 List 데이터로 View가 알아서 바뀝니다.
 * 삭제되거나 추가된다면, 그렇게 처리가 된 전체 리스트를 다 넘겨주면
 * 알아서 이놈이 없어진걸 감지해서 알아서 다 해줍니다.
 *
 * PlaceGridItem을 List로 해서 넣으세요.
 *
 * setItemClickListener로 아이템을 눌렀을 때 하고 싶은 메서드를 추가하세요.
 * 이때 누른 PlaceGridItem이 떨어집니다.
 *
 * 이걸 호출하는 Fragment에서 양옆 마진을 8dp만 주세요.
 * 아이템 뷰 자체에 양옆 8dp가 적용되어있습니다. (가운데 16dp 간격때문에)
 *
 * 스크린 가로길이 에서 양옆 16dp씩, 그리고 아이템 사이의 16dp 총 48dp를 빼고 나누기 2한
 * 동적 길이를 바탕으로 아이템 뷰의 높이를 동적으로 설정합니다.
 * 쉽게말하면 어디든 사진이 정사각형을 유지한다는뜻임.
 *
 * 그니까 왠만하면 해당 뷰의 길이를 수정하지 않는게 좋습니다.
 *
 */

class PlaceGridItemsAdapter :
    ListAdapter<PlaceGridItem, PlaceGridItemsAdapter.ViewHolder>(DiffItemCallback()) {

    private var itemClickListener: PlaceGridItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GridItemPlaceBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val placeGridItem = getItem(position)
        holder.bind(placeGridItem)
    }

    fun setItemClickListener(itemClickListener: PlaceGridItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(
        private val binding: GridItemPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(placeGridItem: PlaceGridItem) {
            binding.item = placeGridItem
            binding.clickListener = itemClickListener
            val layoutParams = binding.cardImage.layoutParams.apply {
                height = getDynamicHeight()
            }
            binding.cardImage.layoutParams = layoutParams
        }

        private fun getDynamicHeight(): Int {
            val context = binding.root.context
            val screenWidth = context.resources.displayMetrics.widthPixels
            return (screenWidth - (16f * 3).toPx()) / 2
        }

        private fun Float.toPx(): Int =
            (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<PlaceGridItem>() {
        override fun areItemsTheSame(oldItem: PlaceGridItem, newItem: PlaceGridItem): Boolean {
            return oldItem.placeIdx == newItem.placeIdx
        }

        override fun areContentsTheSame(oldItem: PlaceGridItem, newItem: PlaceGridItem): Boolean {
            return oldItem == newItem
        }
    }
}