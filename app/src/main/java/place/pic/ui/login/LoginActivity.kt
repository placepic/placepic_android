package place.pic.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_login.*
import place.pic.R
import place.pic.ui.group.GroupListActivity
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customTextChangedListener


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var isCanSendAuthMessage = true
    private var isShowAnimation = true
    private var userPhone = ""
    private val canRetryTime = AUTH_TIME - RETRY_TIME

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
            btn_login_phone_num_send_message.isEnabled = isEnableSendAuthMessageButton()
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
        loginButtonEnableEvent()
        loginButtonClickEvent()
    }

    private fun loginButtonEnableEvent() {
        et_login_phone_num.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        et_login_phone_num.customTextChangedListener {phoneNumFormTextChangeEvent(it)}
        et_login_phone_auth_input.customTextChangedListener {
            btn_login_agree_and_find_group.isEnabled =
                !it.isNullOrBlank() && !et_login_phone_num.text.isNullOrBlank()
        }
    }

    private fun phoneNumFormTextChangeEvent(text:CharSequence?){
        if (text != userPhone){
            btn_login_phone_num_send_message.isEnabled = true
            return
        }
        btn_login_phone_num_send_message.isEnabled = isEnableSendAuthMessageButton()
    }

    private fun isEnableSendAuthMessageButton(): Boolean =
        (!et_login_phone_num.text.isNullOrBlank()) && isCanSendAuthMessage

    private fun loginButtonClickEvent() {
        btn_login_phone_num_send_message.setOnClickListener(this)
        btn_login_agree_and_find_group.setOnClickListener(this)
    }

    override fun onClick(loginButton: View?) {
        when (loginButton!!.id) {
            R.id.btn_login_phone_num_send_message -> { sendAuthMessage() }
            R.id.btn_login_agree_and_find_group -> {
                val gotoGroupListIntent = Intent(applicationContext, GroupListActivity::class.java)
                startActivity(gotoGroupListIntent)
                finish()
            }
        }
    }

    private fun sendAuthMessage() {
        Toast.makeText(applicationContext, "30초 이후에 인증문자를 다시 받을 수 있습니다.", Toast.LENGTH_LONG).show()
        countDownTimer.start()
        userPhone = et_login_phone_num.text.toString()
        isCanSendAuthMessage = false
        btn_login_phone_num_send_message.isEnabled = isEnableSendAuthMessageButton()
        if (isShowAnimation) {
            animationLoginAuth()
        }
        isShowAnimation = false
    }

    private fun animationLoginAuth() {
        val authNumFormAnim = BindLayoutAnimation<ConstraintLayout>(
            applicationContext, cl_login_phone_auth_num_view_group, R.anim.alpah_slide_up
        )
        val buttonAnimation = BindLayoutAnimation<Button>(
            applicationContext, btn_login_agree_and_find_group, R.anim.load_fade_in
        )
        val textGroupAnim = BindLayoutAnimation<ConstraintLayout>(
            applicationContext, cl_login_term_and_privary, R.anim.load_fade_in
        )
        layoutVisibleSetting()
        authNumFormAnim.startLayoutAnimation()
        buttonAnimation.startLayoutAnimation()
        textGroupAnim.setStartOffsetInAnimation(300)
        textGroupAnim.startLayoutAnimation()
    }

    private fun layoutVisibleSetting() {
        cl_login_phone_auth_num_view_group.visibility = View.VISIBLE
        btn_login_agree_and_find_group.visibility = View.VISIBLE
        cl_login_term_and_privary.visibility = View.VISIBLE
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

