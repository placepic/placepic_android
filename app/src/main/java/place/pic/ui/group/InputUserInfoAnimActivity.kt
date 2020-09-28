package place.pic.ui.group

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_input_user_info_anim.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.GroupSignUpRequest
import place.pic.data.remote.response.BaseResponse
import place.pic.ui.main.MainActivity
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customEnqueue
import place.pic.ui.util.customTextChangedListener
import retrofit2.Response

class InputUserInfoAnimActivity : AppCompatActivity(), View.OnClickListener {

    var groupIdx: Int = 0

    private var isInputName: Boolean = false
    private var isInputGroup: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_user_info_anim)
        init()
    }

    private fun init() {
        initAnimation()
        editTextChangeEventMapping()
        buttonEventMapping()
    }

    private fun initAnimation(){
        val showInputPartEditTextEvent = BindLayoutAnimation<ConstraintLayout>(
            applicationContext,
            cl_input_user_info_part,
            R.anim.spread_down
        )
        val showInputNameForm = BindLayoutAnimation<ConstraintLayout>(
            applicationContext,
            cl_input_user_info_name,
            R.anim.spread_down
        )
        showInputNameForm.setStartOffsetInAnimation(500)
        showInputPartEditTextEvent.setStartOffsetInAnimation(700)
        showInputNameForm.startLayoutAnimation()
        showInputPartEditTextEvent.startLayoutAnimation()
    }

    private fun buttonEventMapping() {
        btn_input_user_info_real_start.setOnClickListener(this)
        img_input_user_info_top_back_btn.setOnClickListener(this)
    }

    private fun editTextChangeEventMapping() {
        et_input_user_name.customTextChangedListener {
            isInputName = !it.isNullOrBlank()
            buttonSetEnabled()
        }
        et_input_user_info_part.customTextChangedListener {
            isInputGroup = !it.isNullOrBlank()
            buttonSetEnabled()
        }
    }

    private fun buttonSetEnabled() {
        if (isInputName && isInputGroup) {
            enableSignUpButton()
            return
        }
        disableSignUpButton()
    }

    private fun enableSignUpButton() {
        btn_input_user_info_real_start.isEnabled = true
    }

    private fun disableSignUpButton() {
        btn_input_user_info_real_start.isEnabled = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        previousActivityAnimation()
    }

    override fun finishAffinity() {
        super.finishAffinity()
        nextActivityAnimation()
    }

    override fun onClick(button: View?) {
        when (button!!.id) {
            R.id.btn_input_user_info_real_start ->requestGroupSignUp()
            R.id.img_input_user_info_top_back_btn -> onBackPressed()
        }
    }

    private fun requestGroupSignUp(){
        PlacePicService.getInstance()
            .requestGroupSignUp(
                token = PlacepicAuthRepository.getInstance(applicationContext).userToken!!,
                body = GroupSignUpRequest(
                    userName = et_input_user_name.text.toString(),
                    part = et_input_user_info_part.text.toString(),
                    groupIdx = PlacepicAuthRepository.getInstance(applicationContext).groupId!!
                )
            )
            .customEnqueue(
                onSuccess = {requestSuccessInGroupSignUp()},
                onError = {response -> requestErrorInGroupSignUp(response) }
            )
    }

    private fun requestSuccessInGroupSignUp(){
        val gotoMainIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(gotoMainIntent)
        finishAffinity()
    }

    private fun requestErrorInGroupSignUp(response: Response<BaseResponse<Unit>>) {
        when (response.body()?.status) {
            500 -> {
                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


}