/*
package place.pic.ui.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_input_user_info.*
import place.pic.R
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customTextChangedListener

class InputUserInfoActivity : AppCompatActivity() {

    var groupIdx: Int = 0

    private var isInputGroup: Boolean = false
    private var isInputPhoneNum: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_user_info)
        init()
    }

    private fun init() {
        editTextChangeEventMapping()
        buttonEventMapping()
    }

    private fun buttonEventMapping() {
        btn_input_user_info_real_start.setOnClickListener {
        }
        img_input_user_info_top_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun editTextChangeEventMapping() {
        et_input_user_name.customTextChangedListener {
            isInputGroup = !it.isNullOrBlank()
            buttonSetEnabled()
        }
        et_input_user_info_part.customTextChangedListener {
            isInputPhoneNum = !it.isNullOrBlank()
            buttonSetEnabled()
        }
    }

    private fun buttonSetEnabled() {
        if (isInputGroup && isInputPhoneNum) {
            Log.d("버튼 활성화", "버튼 활성화  됨.")
            enableSignUpButton()
            return
        }
        Log.d("버튼 비활성화", "버튼 비활성화.")
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

    override fun finish() {
        super.finish()
        nextActivityAnimation()
    }

}*/
