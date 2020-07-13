package place.pic.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup_second.*
import place.pic.R
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.RequestRegisterSecond
import place.pic.data.remote.response.BaseResponse
import place.pic.showToast
import place.pic.ui.extands.customTextChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class SignupSecondActivity : AppCompatActivity() {
    lateinit var datePicker : DatePickerHelper
    private var writeSignName = false
    private var writeSignBirth = false
    private var writeSexCode=false
    var sexcode:Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_second)
        val contactemail = intent.getStringExtra("contact_email").toString()
        val contactpassword = intent.getStringExtra("contact_password").toString()

        datePicker= DatePickerHelper(this, true)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        img_sign_top_bar_back_btn2.setOnClickListener {
            onBackPressed()
            overridePendingTransition(0, 0)
            //이전 화면으로 이동
        }

        et_sign_birth.setOnClickListener {
            datePicker.showDialog(day, month, year, object : DatePickerHelper.Callback {
                override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                    et_sign_birth.setText(""  + year + "." + (month+1) + "." + dayofMonth)
                    BirthCheck()
                }})
        }
        SignButtonActivation()

        //중복 선택 막기위한 코드
        btn_signup_men.setOnClickListener{
           if (btn_signup_men.isChecked()) {
               btn_signup_women.isChecked = false
               btn_signup_gitar.isChecked = false
               sexcode=0
               writeSexCode=true;
           }
            if(!btn_signup_men.isChecked())
            {
                sexcode=3
                writeSexCode=false;
            }
            Check(btn_signup_men)
       }

        btn_signup_women.setOnClickListener{
            if (btn_signup_women.isChecked())
            {
                btn_signup_men.isChecked = false
                btn_signup_gitar.isChecked = false
                sexcode=1
                writeSexCode=true;
            }
            if(!btn_signup_women.isChecked())
            {
                sexcode=3
                writeSexCode=false;
            }
            Check(btn_signup_women)
        }

        btn_signup_gitar.setOnClickListener{
            if (btn_signup_gitar.isChecked())
            {
                btn_signup_women.isChecked = false
                btn_signup_men.isChecked = false
                sexcode=2
                writeSexCode=true;
            }
            if(!btn_signup_gitar.isChecked())
            {
                sexcode=3
                writeSexCode=false;
            }
            Check(btn_signup_gitar)
        }

        btn_signnup_second.setOnClickListener {

            PlacePicService.getInstance().requestRegisterSecond(
                RequestRegisterSecond(
                    contactemail,
                    contactpassword,
                    et_sign_name.text.toString(),
                    et_sign_birth.text.toString(),
                    sexcode
                )
            ).enqueue(object: Callback<BaseResponse<Unit>> {
                override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<BaseResponse<Unit>>,
                    response: Response<BaseResponse<Unit>>
                ) {
                    if(response.isSuccessful)
                    {
                        if(response.body()!!.success)
                        {
                            val intent = Intent(this@SignupSecondActivity, SignuplastActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                            startActivity(intent)
                            finish()
                            overridePendingTransition(0, 0)
                        }
                    }
                    else
                    {
                        showToast("회원가입 불가합니다")
                    }
                }
            })
        }

        et_sign_name.customTextChangedListener {
            writeSignName = !it.isNullOrBlank()
            SignButtonActivation()
        }

    }

    private fun Check(button: ToggleButton)
    {
        if(button.isChecked==true)
         {
             writeSexCode=true
         }
        SignButtonActivation()
    }

    private fun BirthCheck()
    {
        if(et_sign_birth.text.toString().isBlank()==false) {
            writeSignBirth = true
        }
        SignButtonActivation()
    }


    private fun SignButtonActivation() {
        btn_signnup_second.isEnabled = writeSignName && writeSexCode && writeSignBirth
    }
}


