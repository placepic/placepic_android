package place.pic.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import place.pic.R
import place.pic.ui.group.GroupListActivity
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.customTextChangedListener


/*
* Button 관련
* 버튼이 활성화 되면
* 버튼 backgroundTint = black40
* Text color = white*/
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var isCanSendAuthMessage = false
    private val canRetryTime = AUTH_TIME - RETRY_TIME

    private val countDownTimer = object : CountDownTimer(AUTH_TIME, ONE_SEC) {
        override fun onTick(millisUntilFinished: Long) {
            canRetrySendAuthMessage(millisUntilFinished)
        }

        override fun onFinish() {
            timeOUtAuthNumEvent()
        }

    }

    private fun canRetrySendAuthMessage(millisUntilFinished: Long) {
        if (millisUntilFinished <= canRetryTime) {
            btn_login_phone_num_send_message.text = "인증문자 다시 받기"
            btn_login_phone_num_send_message.isEnabled = true
            isCanSendAuthMessage = true
        }
        tv_login_can_auth_timer.text = convertMillisTime(millisUntilFinished / ONE_SEC)
    }

    private fun timeOUtAuthNumEvent() {
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

    private fun convertMillisTime(time: Long): String = "0${time/60}:${time%60}"

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
        et_login_phone_num.customTextChangedListener {
            btn_login_phone_num_send_message.isEnabled = !it.isNullOrBlank()
        }
        et_login_phone_auth_input.customTextChangedListener {
            btn_login_agree_and_find_group.isEnabled = !it.isNullOrBlank()
        }
    }

    private fun loginButtonClickEvent() {
        btn_login_phone_num_send_message.setOnClickListener(this)
        btn_login_agree_and_find_group.setOnClickListener(this)
    }

    override fun onClick(loginButton: View?) {
        when (loginButton!!.id) {
            R.id.btn_login_phone_num_send_message -> sendAuthMessage()
            R.id.btn_login_agree_and_find_group -> {
                var gotoGroupListIntent = Intent(applicationContext, GroupListActivity::class.java)
                startActivity(gotoGroupListIntent)
                finish()
            }
        }
    }

    private fun sendAuthMessage() {
        Toast.makeText(applicationContext, "30초 이후에 인증문자를 다시 받을 수 있습니다.", Toast.LENGTH_LONG).show()
        countDownTimer.start()
        btn_login_phone_num_send_message.isEnabled = false

    }

    companion object {
        const val AUTH_TIME = 300000L //5분
        const val RETRY_TIME = 30000L//30초
        const val ONE_SEC = 1000L
    }

}

