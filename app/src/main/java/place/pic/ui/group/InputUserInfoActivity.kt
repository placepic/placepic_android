package place.pic.ui.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_input_user_info.*
import place.pic.R
import place.pic.ui.util.customTextChangedListener

class InputUserInfoActivity : AppCompatActivity() {

    var groupIdx: Int = 0

    private var isInputGroup: Boolean = false
    private var isInputPhoneNum: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_user_info)
        init()
    }

    private fun init() {
        editTextChangeEventMapping()
        buttonEventMapping()
    }

    private fun buttonEventMapping() {
        btn_sign_up_group.setOnClickListener {
        }
        img_sign_up_group_top_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun editTextChangeEventMapping() {
        et_input_user_name.customTextChangedListener {
            isInputGroup = !it.isNullOrBlank()
            buttonSetEnabled()
        }
        et_sign_up_phone_num.customTextChangedListener {
            isInputPhoneNum = !it.isNullOrBlank()
            buttonSetEnabled()
        }
    }

    private fun buttonSetEnabled() {
        if (isInputGroup && isInputPhoneNum) {
            Log.d("버튼 활성화", "버튼 활성화  됨.")
            enableSignUpButton()
            return
        }
        Log.d("버튼 비활성화", "버튼 비활성화.")
        disableSignUpButton()
    }

    private fun enableSignUpButton() {
        btn_sign_up_group.isEnabled = true
    }

    private fun disableSignUpButton() {
        btn_sign_up_group.isEnabled = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.alpah_slide_in_left_to_right,
            R.anim.load_fade_out
        )
    }

   /* *//*서버 연결 *//*
    private fun requestToSignupGroup() {
        PlacepicAuthRepository.getInstance(this).userToken?.let {
            PlacePicService.getInstance()
                .requestSigninGroup(
                    token = it,
                    body = RequestSigninGroup(
                        part = et_sign_up_group.text.toString(),
                        phoneNumber = et_sign_up_phone_num.text.toString(),
                        groupIdx = groupIdx
                    )
                )
                .customEnqueue(
                    onSuccess = { response ->
                        val gotoEndGroupJoinIntent =
                            Intent(this, EndGroupJoinActivity::class.java)
                        response.body()?.data?.let { data ->
                            gotoEndGroupJoinIntent.putExtra("groupName", data.groupName)
                            gotoEndGroupJoinIntent.putExtra("groupImage", data.groupImage)
                        }
                        startActivity(gotoEndGroupJoinIntent)
                        finishAffinity()
                    }
                )
        }
    }*/
}