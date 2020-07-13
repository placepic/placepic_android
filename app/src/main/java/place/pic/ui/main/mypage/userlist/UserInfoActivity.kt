package place.pic.ui.main.mypage.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_info.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.RequestAcceptUser
import place.pic.data.remote.response.ResponseWaitUser
import place.pic.ui.extands.customEnqueue

class UserInfoActivity : AppCompatActivity(), View.OnClickListener {

    private var userInfo:ResponseWaitUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        userInfo = intent.getParcelableExtra<ResponseWaitUser>("WaitUser")!!
        init()
    }

    private fun init() {
        bindUserInfo()
        buttonEventMapping()
    }

    private fun buttonEventMapping() {
        img_user_info_top_back_btn.setOnClickListener(this)
        btn_user_reject.setOnClickListener(this)
        btn_user_accept.setOnClickListener(this)
    }

    private fun bindUserInfo() {
        tv_user_info_name.text = userInfo!!.userName
        tv_user_info_group.text = userInfo!!.part
        tv_user_info_birth.text = userInfo!!.userBirth
        tv_user_info_phone.text = userInfo!!.phoneNumber
        tv_user_info_gender.text = selectGender(userInfo!!.gender)
    }

    private fun selectGender(gender: Int): String {
        return when (gender) {
            GENDER_MAN -> "남성"
            GENDER_GIRL -> "여성"
            GENDER_NON -> "기타"
            else -> throw IllegalArgumentException("접근 할 수 없는 성별 상수 접근입니다.")
        }
    }

    override fun onClick(btn: View?) {

        val token = PlacepicAuthRepository.getInstance(this).userToken ?: return

        when (btn!!.id) {
            R.id.img_user_info_top_back_btn -> {
                onBackPressed()
            }
            R.id.btn_user_reject -> {
                requestToRejectUser(token)
            }
            R.id.btn_user_accept -> {
                requestToAccetUser(token)
            }
        }
    }

    /*서버 연결*/
    private fun requestToAccetUser(token: String) {
        PlacePicService
            .getInstance()
            .requestAcceptUser(
                token = token,
                groupIdx = userInfo!!.groupIdx,
                body = RequestAcceptUser(userIdx = userInfo!!.userIdx)
            ).customEnqueue(
                onSuccess = { response ->
                    Toast
                        .makeText(this, response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                    onBackPressed()
                }
            )
    }

    private fun requestToRejectUser(token: String) {
        PlacePicService
            .getInstance()
            .requestRejectUser(
                token = token,
                groupIdx = userInfo!!.groupIdx,
                userIdx = userInfo!!.userIdx
            ).customEnqueue(
                onSuccess = {response->
                    Toast
                        .makeText(this, response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                    onBackPressed()
                }
            )
    }

    companion object {
        private const val GENDER_MAN = 0
        private const val GENDER_GIRL = 1
        private const val GENDER_NON = 2
    }
}