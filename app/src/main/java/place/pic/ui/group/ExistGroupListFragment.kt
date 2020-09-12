package place.pic.ui.group

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_exist_group_list.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.main.MainActivity
import java.lang.NullPointerException

class ExistGroupListFragment : Fragment(),BindGroupListEvent {

    lateinit var existGroupListAdapter: ExistGroupListAdapter
    private val groupListData: MutableList<ResponseGroupList> = mutableListOf()
    private var requestGroupListListener: (() -> Unit)? = null

    fun setRequestGroupListListener(listener: () -> Unit) {
        this.requestGroupListListener = listener
    }

    override fun clickSignGroupListener() {
        val gotoMain = Intent(requireActivity(), MainActivity::class.java)
        startActivity(gotoMain)
        requireActivity().finish()
    }

    override fun clickNonSignGroupListener() {

    }

    override fun saveGroupIDListener(id: Int) {
        PlacepicAuthRepository.getInstance(requireContext()).saveGroupId(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exist_group_list, container, false)
    }

    private fun hasRequestGroupListener(){
        if (requestGroupListListener == null) {
            throw NullPointerException("그룹 리스트 리스너가 null 입니다. setRequestGroupListListener 를 사용하여 listener 를 만들어주세요.")
        }
        Log.d("hasListener","GroupListFragment has listener")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hasRequestGroupListener()
        init()
        setAdapter()
    }

    private fun init() {
        loadData()//FIXME 추후 서버 연결 로직으로 변경하기.
        requestGroupListListener?.invoke()
    }

    private fun setAdapter() {
        existGroupListAdapter =
            ExistGroupListAdapter(
                groupListData,
                requireContext(),
                this
            )
        rv_exist_group_list.adapter = existGroupListAdapter
    }

    private fun loadData() {
        groupListData.apply {
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "가입된 그룹",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 1,
                    userIdx = 8
                )
            )
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "가입된 그룹",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 1,
                    userIdx = 8
                )
            )
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "미가입 그룹",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 2,
                    userIdx = 8
                )
            )
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "가입된 그룹",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 1,
                    userIdx = 8
                )
            )
            add(
                ResponseGroupList(
                    postCount = 1111,
                    userCount = 16,
                    groupIdx = 4,
                    groupImage = "https://avatars2.githubusercontent.com/u/67547341?s=200&v=4",
                    groupName = "미가입 그룹",
                    groupUserIdx = 0,
                    part = "어찌구파트",
                    phoneNumber = "01011112222",
                    state = 2,
                    userIdx = 8
                )
            )
        }
    }
}