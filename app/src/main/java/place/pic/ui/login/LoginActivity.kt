package place.pic.ui.login

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import place.pic.R
import place.pic.ui.extands.customTextChangedListener
import android.util.Patterns
import androidx.core.content.ContextCompat

/*
* Button 관련
* 버튼이 활성화 되면
* 버튼 backgroundTint = black40
* Text color = white*/
class LoginActivity : AppCompatActivity(),View.OnClickListener {

    private var writeEmail = false
    private var writePassword = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        buttonMapping()
        editTextChangedMapping()
    }

    private fun buttonMapping(){
        img_login_top_bar_back_btn.setOnClickListener(this)

    }

    private fun editTextChangedMapping(){
        et_login_email.customTextChangedListener {
            if (isValidEmail(it.toString())) {
                emailForm()
                writeEmail = true
                return@customTextChangedListener
            }
            notEmailForm()
            loginButtonActivation()
        }

        et_login_password.customTextChangedListener {
            writePassword = !it.isNullOrBlank()
            loginButtonActivation()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_login_top_bar_back_btn -> onBackPressed()
            R.id.btn_login -> {

            }
        }
    }

    private fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // 잘못된 이메일일 경우 EditText 설정
    private fun notEmailForm(){
        et_login_email.background = getDrawable(R.drawable.border_round_rectangle_red_8dp)
        tv_login_non_email.visibility = View.VISIBLE
    }

    // 이메일 입력이 정확할 경우 EditText 설정
    private fun emailForm(){
        et_login_email.background = getDrawable(R.drawable.selector_edittext_in_login_view)
        tv_login_non_email.visibility = View.INVISIBLE
    }

    //로그인 버튼 활성화
    private fun loginButtonActivation(){
        btn_login.isEnabled = writeEmail && writePassword
    }
}
