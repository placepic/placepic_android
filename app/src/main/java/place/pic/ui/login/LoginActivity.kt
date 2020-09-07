package place.pic.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.RequestLogin
import place.pic.data.remote.response.BaseResponse
import place.pic.data.remote.response.LoginResponse
import place.pic.ui.util.customTextChangedListener
import place.pic.ui.group.GroupListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*
* Button 관련
* 버튼이 활성화 되면
* 버튼 backgroundTint = black40
* Text color = white*/
class LoginActivity : AppCompatActivity(),View.OnClickListener {

    private var writeEmail = false
    private var writePassword = false


    override fun onBackPressed() {
        super.onBackPressed()
        val gotoLoginPageIntent = Intent(this,LoginPageActivity::class.java)
        gotoLoginPageIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(gotoLoginPageIntent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sp = getSharedPreferences("temp", Context.MODE_PRIVATE)
        val s: String? = sp.getString("id", "")
        val b: Boolean = sp.getBoolean("cb", false)

        et_login_email.setText(s)
        et_login_email.background = getDrawable(R.drawable.selector_edittext_in_login_view)
        tv_login_non_email.visibility = View.INVISIBLE
        cb_login.isChecked = b
        writeEmail = true
        init()
    }

    private fun init() {
        buttonMapping()
        editTextChangedMapping()
    }

    private fun buttonMapping(){
        img_login_top_bar_back_btn.setOnClickListener(this)
        btn_login_agree_and_find_group.setOnClickListener(this)
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
            R.id.img_login_top_bar_back_btn -> {
                val gotoLoginPageIntent = Intent(this,LoginPageActivity::class.java)
                gotoLoginPageIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(gotoLoginPageIntent)
                finish()
            }
            R.id.btn_login_agree_and_find_group -> {
                PlacePicService.getInstance().requestLogin(
                    RequestLogin(
                        et_login_email.text.toString(),
                        et_login_password.text.toString()
                    )
                ).enqueue(object: Callback<BaseResponse<LoginResponse>> {
                    override fun onFailure(call: Call<BaseResponse<LoginResponse>>, t: Throwable) {
                        //통신실패
                    }
                    override fun onResponse(
                        call: Call<BaseResponse<LoginResponse>>,
                        response: Response<BaseResponse<LoginResponse>>) {
                        if(response.isSuccessful)
                        {
                            if(response.body()!!.success)
                            {
                                PlacepicAuthRepository
                                    .getInstance(this@LoginActivity)
                                    .saveUserToken(response.body()!!.data.accessToken)

                                val intent = Intent(this@LoginActivity, GroupListActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                        }
                        else {
                            tv_login_fail.visibility = View.VISIBLE
                        }
                    }
                })
            }
        }
        editTextChangedMapping()
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
        btn_login_agree_and_find_group.isEnabled = writeEmail && writePassword
    }

    override fun onStop() {
        super.onStop()
        val prefer = getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor = prefer.edit()

        if(cb_login.isChecked) {
            editor.putString("id", et_login_email.text.toString())
            editor.putBoolean("cb", cb_login.isChecked)
            editor.putBoolean("bb", btn_login_agree_and_find_group.isEnabled)
        }
        editor.apply()
    }
}

