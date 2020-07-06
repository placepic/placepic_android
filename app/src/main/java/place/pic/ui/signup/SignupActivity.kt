package place.pic.ui.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import place.pic.R
import place.pic.showToast
import place.pic.ui.extands.customTextChangedListener
import place.pic.ui.login.LoginActivity

class SignupActivity : AppCompatActivity() {

    private var writeSignEmail = false
    private var writeSignPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        init()
        img_sign_top_bar_back_btn.setOnClickListener {
            //로그인 화면으로 가버령
            finish()
        }
        btn_signnup.setOnClickListener {
            if (et_sign_email.text.isNullOrBlank() || et_sign_password.text.isNullOrBlank() || et_sign_ok_password.text.isNullOrBlank())
            {
                //빈칸이 있을 때
                showToast("빈칸이있어용")
            }
            else if(et_sign_password.getText().toString() != et_sign_ok_password.getText().toString()) {
                et_sign_ok_password.background = getDrawable(R.drawable.border_round_rectangle_red_8dp)
                tv_sign_non_password.visibility = View.VISIBLE
            }
            else {
                et_sign_ok_password.background = getDrawable(R.color.selector_edittext_in_login_view)
                tv_sign_non_password.visibility = View.INVISIBLE

                val intent = Intent(this, SignupSecondActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }
    }

    private fun init() {
        editTextChangedMapping()
    }

    private fun editTextChangedMapping(){
        et_sign_email.customTextChangedListener {
            if (isValidEmail(it.toString())) {
                emailForm()
                writeSignEmail = true
                return@customTextChangedListener
            }
            notEmailForm()
            SignButtonActivation()
        }

        et_sign_ok_password.customTextChangedListener {
            writeSignPassword = !it.isNullOrBlank()
            SignButtonActivation()
        }
    }

    private fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // 잘못된 이메일일 경우 EditText 설정
    private fun notEmailForm(){
        et_sign_email.background = getDrawable(R.drawable.border_round_rectangle_red_8dp)
        tv_sign_non_email.visibility = View.VISIBLE
    }

    // 이메일 입력이 정확할 경우 EditText 설정
    private fun emailForm(){
        et_sign_email.background = getDrawable(R.color.selector_edittext_in_login_view)
        tv_sign_non_email.visibility = View.INVISIBLE
    }

    //로그인 버튼 활성화
    private fun SignButtonActivation(){
        btn_signnup.isEnabled = writeSignEmail && writeSignPassword
    }
}

