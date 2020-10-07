package place.pic.ui.group

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_group_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.main.MainActivity
import place.pic.ui.util.animation.nextActivityAnimation

class GroupListFragment : Fragment() {

    private var groupListAdapter: GroupListAdapter? = null

    private var bindGroupListEvent: BindGroupListEvent? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is BindGroupListEvent) {
            throw RuntimeException(context.toString() + "GroupListFragment 를 사용하기 위해서는 BindGroupListEvent 를 implement 해야합니다.")
        }
        bindGroupListEvent = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        Log.d("TestGroupList","GroupList Test")
        bindGroupListEvent?.requestToGroupListData()
    }

    fun setAdapter(groupListData: List<ResponseGroupList>) {
        groupListAdapter = GroupListAdapter()
        groupListAdapter?.datas = groupListData
        groupListAdapter?.setSaveGroupIDListener { id -> saveGroupIdEvent(id) }
        groupListAdapter?.setClickSignGroupListener { clickSignGroupEvent() }
        groupListAdapter?.setClickNonSignGroupListener { groupCode ->
            clickNonSignGroupEvent(groupCode)
        }
        groupListAdapter?.notifyDataSetChanged()
        rv_exist_group_list.adapter = groupListAdapter
    }

    private fun saveGroupIdEvent(groupId: Int) {
        val placePicRepository = PlacepicAuthRepository.getInstance(requireContext())
        placePicRepository.removeGroupId()
        placePicRepository.saveGroupId(groupId)
    }

    private fun clickSignGroupEvent() {
        val gotoMainIntent = Intent(requireContext(), MainActivity::class.java)
        startActivity(gotoMainIntent)
        requireActivity().finishAffinity()
    }

    private fun clickNonSignGroupEvent(groupCode: String) {
        val gotoGroupSignUpIntent = Intent(requireContext(), InputVisitCodeActivity::class.java)
        gotoGroupSignUpIntent.putExtra("groupCode", groupCode)
        startActivity(gotoGroupSignUpIntent)
        requireActivity().nextActivityAnimation()
    }


}