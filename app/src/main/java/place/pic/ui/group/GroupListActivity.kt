package place.pic.ui.group

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.ResponseGroupList
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customEnqueue
import retrofit2.Response

class GroupListActivity : AppCompatActivity(), BindGroupListEvent {

    private var groupListFragment = GroupListFragment()

    override fun requestToGroupListData() {
        PlacePicService.getInstance()
            .requestGroupList(
                token = PlacepicAuthRepository.getInstance(applicationContext).userToken!!
            )
            .customEnqueue(
                onSuccess = { response ->
                    responseSuccessInGroupListData(response)
                },
                onError = { response ->
                    requestErrorInGroupListData(response)
                }
            )
    }

    private fun responseSuccessInGroupListData(response: Response<BaseResponse<List<ResponseGroupList>>>) {
        val groupList = mutableListOf<ResponseGroupList>()
        groupList.add(applyGroupButton())
        groupList.addAll(response.body()?.data!!)
        groupListFragment.setAdapter(groupList.toList())
    }

    private fun applyGroupButton(): ResponseGroupList {
        return ResponseGroupList(
            postCount = 0,
            userCount = 0,
            groupImage = "",
            groupIdx = -101,
            groupCode = "",
            groupName = "",
            state = 0
        )
    }

    private fun requestErrorInGroupListData(response: Response<BaseResponse<List<ResponseGroupList>>>) {
        when (response.body()?.status) {
            500 -> {
                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_list)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_group_list, groupListFragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        previousActivityAnimation()
    }

    override fun finish() {
        super.finish()
        nextActivityAnimation()
    }
}