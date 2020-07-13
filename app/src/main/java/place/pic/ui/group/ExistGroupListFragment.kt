package place.pic.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_exist_group_list.*
import place.pic.R
import place.pic.data.remote.response.ResponseGroupList

class ExistGroupListFragment(
    val groupListData:List<ResponseGroupList>
) : Fragment(){

    lateinit var existGroupListAdapter: ExistGroupListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exist_group_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_exist_group_list.addItemDecoration(
            DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL)
        )
        setAdpater()
    }

    private fun setAdpater(){
        existGroupListAdapter = ExistGroupListAdapter(groupListData, this.context!!)
        rv_exist_group_list.adapter = existGroupListAdapter
    }
}