package place.pic.ui.main.mypage.userlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_user_list.*
import place.pic.R
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.UserListResponse
import retrofit2.Callback
import retrofit2.Response


class UserListFragment : Fragment() {

    lateinit var userListAdapter: UserListAdapter
    lateinit var layoutManager: LinearLayoutManager
    private var data: MutableList<UserData> = mutableListOf()

    var userCount: Int = 0
    val userInUserList: MutableList<UserData> =
        mutableListOf<UserData>()?.apply { add(0, UserData.empty()) }
    //서버 연결 테스트용

    private val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjMsIm5hbWUiOiLstZzsmIHtm4giLCJpYXQiOjE1OTM2OTkxODMsImV4cCI6MTU5NjI5MTE4MywiaXNzIjoicGxhY2VwaWMifQ.rmFbeBfviyEzbMlMM4b3bMMiRcNDDbiX8bQtwL_cuN0"

    private val placePicService = PlacePicService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcv(view)
        getUserListFromServer(1)
        //loadDatas()
        //loadDatas() 호출을 통해 infinite scroll을 위한 준비 완료
    }

    private fun initRcv(view: View) {
        userListAdapter = UserListAdapter()
        recyclerview_user_list.adapter = userListAdapter
    }

    /*private fun loadDatas() {
        data = stubUserList() as MutableList<UserData>
        userListAdapter.addItems(data)
    }*/

    private fun getUserListFromServer(groupIdx: Int) {
        placePicService.getInstance()
            .requestUserList(
                token = token,
                groupIdx = groupIdx
            ).enqueue(object : Callback<BaseResponse<UserListResponse>> {
                override fun onFailure(
                    call: retrofit2.Call<BaseResponse<UserListResponse>>,
                    t: Throwable
                ) { //통신 실패
                    Log.d("fail", t.message)
                }

                override fun onResponse(
                    call: retrofit2.Call<BaseResponse<UserListResponse>>,
                    response: Response<BaseResponse<UserListResponse>>
                ) {
                    //통신 성공
                    if (response.isSuccessful) { //status
                        Log.d("typeCheck", "통신성공")
                        if (response.body()!!.success) {
                            for (i in response.body()!!.data.userList.indices) {
                                userCount = response.body()!!.data.userCnt
                                userInUserList.apply {
                                    add(
                                        UserData(
                                            rank = response.body()!!.data.userList[i].rank,
                                            userIdx = response.body()!!.data.userList[i].userIdx,
                                            userName = response.body()!!.data.userList[i].userName,
                                            profileImageUrl = response.body()!!.data.userList[i].profileImageUrl,
                                            state = response.body()!!.data.userList[i].state,
                                            part = response.body()!!.data.userList[i].part,
                                            postCount = response.body()!!.data.userList[i].postCount
                                        )
                                    )
                                }
                                userListAdapter.userCount = userCount
                                userListAdapter.addItems(userInUserList)
                                userListAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            })
    }
}

