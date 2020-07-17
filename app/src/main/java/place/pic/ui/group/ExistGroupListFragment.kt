package place.pic.ui.group

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_exist_group_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.main.MainActivity

class ExistGroupListFragment(
    private val groupListData: List<ResponseGroupList>,
    private val buttonLayout: ConstraintLayout
) : Fragment() {

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
        if (buttonLayout.visibility == View.VISIBLE) {
            rv_exist_group_list.setPadding(0,buttonLayout.height,0,0)
        }
        setAdpater()
    }

    private fun setAdpater() {
        existGroupListAdapter =
            ExistGroupListAdapter(
                groupListData,
                this.context!!,
                PlacepicAuthRepository.getInstance(requireContext())
            ){
                existDetailClickEvent()
            }
        rv_exist_group_list.adapter = existGroupListAdapter
    }

    private fun existDetailClickEvent(){
        Log.d("ExistDetailClick","Detail Click")
        val gotoMain = Intent(requireActivity(),MainActivity::class.java)
        startActivity(gotoMain)
        requireActivity().finish()
    }
}