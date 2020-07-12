package place.pic.ui.search.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.R

class PlaceSearchAdapter : RecyclerView.Adapter<PlaceSearchViewHolder>() {

    var datas: MutableList<PlaceSearchData> = mutableListOf<PlaceSearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //가독성을 위하여
        //context를 매개변수 받을 필요가 없어
        //왜? context 쓰는건 inflater 뿐인데 굳이 필요없는데 받을 필요가 없잖아
        //항상 context를 가지고 있는 얘가 있어 -> 그게 바로 view
        //따라서 context를 받지 않고 viewGroup인 parent에서 context를 뽑아 쓰면 돼
        val view = layoutInflater.inflate(R.layout.item_place_search, parent, false)
        return PlaceSearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: PlaceSearchViewHolder, position: Int) {
        holder.bind(datas[position])

        //view에 onClickListener를 달고 그 안에서 직접 만든 itemClickListener를 연결
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    //클릭인터페이스 정의
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    
    //클릭리스너 선언
    private lateinit var itemClickListener: ItemClickListener

    //클릭리스너 등록 메소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}