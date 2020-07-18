package place.pic.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup_second.*
import place.pic.R
import place.pic.data.remote.PlacePicService
import place.pic.data.remote.request.RequestRegisterSecond
import place.pic.data.remote.response.BaseResponse
import place.pic.ui.util.showToast
import place.pic.ui.util.customTextChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

//회원가입 두번째 페이지

class SignupSecondActivity : AppCompatActivity() {
    lateinit var datePicker : DatePickerHelper
    private var writeSignName = false
    private var writeSignBirth = false
    private var writeSexCode = false
    var sexcode:Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_second)

        //날짜 Picker
        datePicker= DatePickerHelper(this, true)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //back button
        img_sign_top_bar_back_btn2.setOnClickListener {
            onBackPressed()
            overridePendingTransition(0, 0) //애니메이션 없앰
        }

        //이름 빈칸 확인
        SignNameCheck()

        //생일 edit text 클릭 시
        SignBirhCheck(day,month,year)

        //성별 버튼 클릭 시
        SexCheck()

        //회원가입 버튼 클릭 시
        SignUpButtonCheck()
    }

    //이름 체크
    private fun SignNameCheck()
    {
        et_sign_name.customTextChangedListener {
            writeSignName = !it.isNullOrBlank()
            SignButtonActivation()
        }
    }

    //생일 체크
    private fun SignBirhCheck(day: Int, month: Int, year: Int)
    {
        et_sign_birth.setOnClickListener {
            datePicker.showDialog(day, month, year, object : DatePickerHelper.Callback {
                override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                    et_sign_birth.text =("$year.${month+1}.$dayofMonth")
                    BirthCheck() //빈칸 체크
                }
            })
        }
    }

    //성별 체크
    private fun SexCheck()
    {
        btn_signup_men.setOnClickListener{
            if (btn_signup_men.isChecked()) {
                btn_signup_women.isChecked = false
                btn_signup_gitar.isChecked = false
                sexcode=0 //남성 코드
                writeSexCode=true;
            } else {
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
                sexcode=1 //여성 코드
                writeSexCode=true;
            } else {
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
                sexcode=2 //기타 코드
                writeSexCode=true;
            } else {
                sexcode=3
                writeSexCode=false;
            }
            Check(btn_signup_gitar)
        }
    }

    //회원가입 버튼 체크
    private fun SignUpButtonCheck()
    {
        val contactemail = intent.getStringExtra("contact_email")!!.toString()
        val contactpassword = intent.getStringExtra("contact_password")!!.toString()

        btn_signnup_second.setOnClickListener {
            PlacePicService.getInstance().requestRegisterSecond(
                RequestRegisterSecond(
                    contactemail,
                    contactpassword,
                    et_sign_name.text.toString(),
                    et_sign_birth.text.toString(),
                    sexcode
                )).enqueue(object: Callback<BaseResponse<Unit>> {
                override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                    //실패시
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
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY) //다음 액티비티부터 스택 지움
                            startActivity(intent)
                            finish()
                            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                        }
                    } else {
                        showToast("회원가입 불가합니다") //동시에 같은 이메일로 가입 시
                    }
                }
            })
        }
    }




    //SEX BUTTON 눌림 체크
    private fun Check(button: ToggleButton)
    {
        if(button.isChecked==true)
        {
            writeSexCode=true
        }
        SignButtonActivation()
    }

    //생일 값 채워짐 체크
    private fun BirthCheck()
    {
        if(et_sign_birth.text.toString().isBlank()==false)
        {
            writeSignBirth = true
        }
        SignButtonActivation()
    }

    //회원가입 버튼 활성화
    private fun SignButtonActivation()
    {
        btn_signnup_second.isEnabled = writeSignName && writeSexCode && writeSignBirth
    }
}


