package place.pic.ui.signup

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.System.DATE_FORMAT
import android.util.Patterns
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup_second.*
import place.pic.R
import place.pic.showToast
import place.pic.ui.extands.customTextChangedListener
import place.pic.ui.login.LoginActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SignupSecondActivity : AppCompatActivity() {

    private var writeSignName = false
    private var writeSignBirth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_second)
        init()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        img_sign_top_bar_back_btn2.setOnClickListener {
            onBackPressed()
            overridePendingTransition(0, 0)
            //이전 화면으로 이동
        }
        btn_sign_men.setOnClickListener {
                btn_sign_men.isEnabled
        }

        btn_signnup_second.setOnClickListener {
            if (et_sign_name.text.isNullOrBlank() || et_sign_birth.text.isNullOrBlank()) //sex도 추가 해야돼!!!!
            {
                //빈칸이 있을 때
                showToast("빈칸이있어용")
            } else {
                //setResult
            }
        }

        et_sign_birth.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    et_sign_birth.setText(""  + year + "." + (month+1) + "." + day)}, year, month, day)

            datePicker.show()
        }
    }

    private fun init() {
       SignButtonActivation()
    }

    private fun SignButtonActivation(){
        btn_signnup_second.isEnabled = writeSignName && writeSignBirth
    }
}

