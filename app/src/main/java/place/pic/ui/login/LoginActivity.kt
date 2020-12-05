package place.pic.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_login.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.LoginAndSignUpAuthNumRequest
import place.pic.data.remote.request.LoginAndSignUpPhoneNumRequest
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.LoginAndSignUpPhoneNumResponse
import place.pic.data.remote.response.LoginResponse
import place.pic.ui.group.GroupListActivity
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customEnqueue
import place.pic.ui.util.customTextChangedListener
import place.pic.ui.webview.InWebActivity
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var isCanSendAuthMessage = true
    private var isShowAnimation = true
    private val canRetryTime = AUTH_TIME - RETRY_TIME

    private val loginAndSignUpAuthNumRequest = LoginAndSignUpAuthNumRequest()

    private val countDownTimer = object : CountDownTimer(AUTH_TIME, ONE_SEC) {
        override fun onTick(millisUntilFinished: Long) {
            canRetrySendAuthMessage(millisUntilFinished)
        }

        override fun onFinish() {
            timeOutAuthNumEvent()
        }

    }

    private fun canRetrySendAuthMessage(millisUntilFinished: Long) {
        if (millisUntilFinished <= canRetryTime) {
            btn_login_phone_num_send_message.text = "인증문자 다시 받기"
            isCanSendAuthMessage = true
        }
        tv_login_can_auth_timer.text = convertMillisTime(millisUntilFinished / ONE_SEC)
    }

    private fun timeOutAuthNumEvent() {
        btn_login_phone_num_send_message.text = "인증문자 받기"
        val bindLayoutAnimation = BindLayoutAnimation<TextView>(
            applicationContext,
            tv_login_auth_num_info,
            R.anim.alpha_splash_text_in
        )
        tv_login_auth_num_info.setTextColor(getColor(R.color.design_default_color_error))
        tv_login_auth_num_info.text = "다시 인증해야 합니다."
        bindLayoutAnimation.startLayoutAnimation()
    }

    private fun convertMillisTime(time: Long): String {
        val min = time / 60
        val sec = time % 60
        return if (sec < 10) {
            "0${min}:0${sec}"
        } else {
            "0${min}:${sec}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        initAnimation()
        loginButtonEnableEvent()
        loginButtonClickEvent()
    }

    private fun initAnimation() {
        val initAnimation = BindLayoutAnimation<ConstraintLayout>(
            applicationContext,
            cl_login_phone_num_view_group,
            R.anim.spread_down
        )
        initAnimation.setStartOffsetInAnimation(500)
        initAnimation.startLayoutAnimation()
    }

    private fun loginButtonEnableEvent() {
        et_login_phone_num.customTextChangedListener {
            btn_login_phone_num_send_message.isEnabled = validateNotNullOf(it)
        }
        et_login_phone_auth_input.customTextChangedListener {
            btn_login_agree_and_find_group.isEnabled = enableFindGroupButtonCondition(it)
        }
        age_condition_check.setOnClickListener {
            val authNum = et_login_phone_auth_input.text
            btn_login_agree_and_find_group.isEnabled = enableFindGroupButtonCondition(authNum)
        }
        agreement_on_terms_and_conditions_check.setOnClickListener {
            val authNum = et_login_phone_auth_input.text
            btn_login_agree_and_find_group.isEnabled = enableFindGroupButtonCondition(authNum)
        }
    }

    private fun validateNotNullOf(value: CharSequence?): Boolean = value?.isNotBlank() ?: false

    private fun enableFindGroupButtonCondition(value: CharSequence?): Boolean {
        return validateNotNullOf(value) && validateNotNullOf(et_login_phone_num.text) && isCheckAllCondition()
    }

    private fun isCheckAllCondition(): Boolean =
        age_condition_check.isChecked && agreement_on_terms_and_conditions_check.isChecked


    private fun loginButtonClickEvent() {
        btn_login_phone_num_send_message.setOnClickListener(this)
        btn_login_agree_and_find_group.setOnClickListener(this)
        img_login_top_bar_back_btn.setOnClickListener(this)
        tv_login_privary.setOnClickListener(this)
        tv_login_term.setOnClickListener(this)
    }

    override fun onClick(loginButton: View?) {
        when (loginButton!!.id) {
            R.id.btn_login_phone_num_send_message -> {
                sendAuthMessage()
            }
            R.id.btn_login_agree_and_find_group -> {
                requestLoginToServer()
            }
            R.id.img_login_top_bar_back_btn -> {
                onBackPressed()
            }
            R.id.tv_login_privary -> {
                loadInWebPage(
                    webTitle = "placepic 개인정보 처리 방침",
                    webUrl = "https://www.notion.so/27f34b61fef0498aa23e285beeb45850"
                )
            }
            R.id.tv_login_term -> {
            }
        }
    }

    private fun sendAuthMessage() {
        canShowSendAuthMessageAnimation()
        if (isCanSendAuthMessage) {
            requestPhoneAuthNumberToServer()
            return
        }
        Toast.makeText(applicationContext, "30초 이후에 인증문자를 다시 받을 수 있습니다.", Toast.LENGTH_LONG).show()
    }

    private fun canShowSendAuthMessageAnimation() {
        if (isShowAnimation) {
            animationLoginAuth()
            isShowAnimation = false
        }
    }

    private fun animationLoginAuth() {
        val authNumFormAnim = BindLayoutAnimation<ConstraintLayout>(
            applicationContext, cl_login_phone_auth_num_view_group, R.anim.spread_down
        )
        layoutVisibleSetting()
        authNumFormAnim.startLayoutAnimation()
    }

    private fun layoutVisibleSetting() {
        cl_login_phone_auth_num_view_group.visibility = View.VISIBLE
    }

    private fun requestPhoneAuthNumberToServer() {
        PlacePicService.getInstance()
            .requestLoginAndSignUpPhoneNum(
                LoginAndSignUpPhoneNumRequest(
                    phoneNumber = et_login_phone_num.text.toString()
                )
            )
            .customEnqueue(
                onSuccess = {
                    countDownTimer.start()
                    isCanSendAuthMessage = false
                },
                onError = { response ->
                    requestErrorInPhoneNum(response)
                }
            )

    }

    private fun requestErrorInPhoneNum(
        response: Response<BaseResponse<LoginAndSignUpPhoneNumResponse>>
    ) {
        when (response.body()?.status) {
            400 -> {
                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                    .show()
            }
            500 -> {
                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun requestLoginToServer() {
        loginAndSignUpAuthNumRequest.apply {
            this.addOnSuccessListener { response -> requestSuccessInLogin(response) }
            this.addOnFailureListener { response -> requestErrorInLogin(response) }
        }.sendData(
            phoneNumber = et_login_phone_num.text.toString(),
            certificationNumber = et_login_phone_auth_input.text.toString()
        )
    }

    private fun requestSuccessInLogin(response: BaseResponse<LoginResponse>) {
        PlacepicAuthRepository.getInstance(applicationContext)
            .saveUserToken(response.data.accessToken)
        val gotoGroupListIntent = Intent(applicationContext, GroupListActivity::class.java)
        startActivity(gotoGroupListIntent)
        finish()
    }

    private fun requestErrorInLogin(response: BaseResponse<Unit>) {
        when (response.status) {
            400 -> {
                Toast.makeText(applicationContext, response.message, Toast.LENGTH_SHORT)
                    .show()
            }
            500 -> {
                Toast.makeText(applicationContext, response.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun loadInWebPage(webTitle: String, webUrl: String) {
        val gotoInWebIntent = Intent(applicationContext, InWebActivity::class.java)
        gotoInWebIntent.putExtra("webUrl", webUrl)
        gotoInWebIntent.putExtra("webTitle", webTitle)
        startActivity(gotoInWebIntent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        previousActivityAnimation()
    }

    override fun finish() {
        super.finish()
        nextActivityAnimation()
    }

    companion object {
        const val AUTH_TIME = 300000L //5분
        const val RETRY_TIME = 30000L//30초
        const val ONE_SEC = 1000L
    }

}

