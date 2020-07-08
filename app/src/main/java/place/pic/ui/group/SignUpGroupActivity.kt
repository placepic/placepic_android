package place.pic.ui.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_sign_up_group.*
import place.pic.R
import place.pic.ui.extands.customTextChangedListener

class SignUpGroupActivity : AppCompatActivity() {

    private var isInputGroup:Boolean = false
    private var isInputPhoneNum:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_sign_up_group)
        init()
    }

    private fun init(){
        editTextChangeEventMapping()
        buttonEventMapping()
    }

    private fun buttonEventMapping(){
        btn_sign_up_group.setOnClickListener {
            val gotoEndGroupJoinIntent = Intent(this, EndGroupJoinActivity::class.java)
            startActivity(gotoEndGroupJoinIntent)
            finish()
        }
        img_sign_up_group_top_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun editTextChangeEventMapping(){
        et_sign_up_group.customTextChangedListener {
            isInputGroup = !it.isNullOrBlank()
            buttonSetEnabled()
        }
        et_sign_up_phone_num.customTextChangedListener {
            isInputPhoneNum = !it.isNullOrBlank()
            buttonSetEnabled()
        }
    }

    private fun buttonSetEnabled(){
        if (isInputGroup && isInputPhoneNum) {
            Log.d("버튼 활성화","버튼 활성화  됨.")
            enableSignUpButton()
            return
        }
        Log.d("버튼 비활성화", "버튼 비활성화.")
        disableSignUpButton()
    }

    private fun enableSignUpButton(){
        btn_sign_up_group.isEnabled= true
    }

    private fun disableSignUpButton(){
        btn_sign_up_group.isEnabled= false
    }
}