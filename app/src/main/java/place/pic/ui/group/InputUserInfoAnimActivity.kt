package place.pic.ui.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_input_user_info_anim.*
import place.pic.R
import place.pic.ui.main.MainActivity
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customTextChangedListener

class InputUserInfoAnimActivity : AppCompatActivity(), View.OnClickListener {

    var groupIdx: Int = 0

    private var isInputName: Boolean = false
    private var isInputGroup: Boolean = false

    lateinit var inputMethodManager:InputMethodManager

    private fun focusEditTextEvent(editText: EditText, previousButton:Button?,nextButton: Button?){
        editText.requestFocus()
        inputMethodManager.showSoftInput(editText, 0)
        previousButton?.visibility = View.GONE
        nextButton?.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_user_info_anim)
        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        init()
    }

    private fun init() {
        initAnimation()
        focusEditTextEvent(et_input_user_info_part,null,btn_input_user_info_end_input_part)
        editTextChangeEventMapping()
        buttonEventMapping()
    }

    private fun initAnimation(){
        val showInputPartEditTextEvent = BindLayoutAnimation<ConstraintLayout>(
            applicationContext,
            cl_input_user_info_part,
            R.anim.spread_down
        )
        showInputPartEditTextEvent.setStartOffsetInAnimation(700)
        showInputPartEditTextEvent.startLayoutAnimation()
    }

    private fun buttonEventMapping() {
        btn_input_user_info_real_start.setOnClickListener(this)
        btn_input_user_info_end_input_part.setOnClickListener(this)
        img_input_user_info_top_back_btn.setOnClickListener(this)
    }

    private fun editTextChangeEventMapping() {
        et_input_user_name.customTextChangedListener {
            isInputName = !it.isNullOrBlank()
            buttonSetEnabled()
        }
        et_input_user_info_part.customTextChangedListener {
            isInputGroup = !it.isNullOrBlank()
            btn_input_user_info_end_input_part.isEnabled = !it.isNullOrBlank()
        }
    }

    private fun buttonSetEnabled() {
        if (isInputName && isInputGroup) {
            enableSignUpButton()
            return
        }
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

    override fun finishAffinity() {
        super.finishAffinity()
        nextActivityAnimation()
    }

    override fun onClick(button: View?) {
        when (button!!.id) {
            R.id.btn_input_user_info_end_input_part ->{showInputNameFormEvent()}
            R.id.btn_input_user_info_real_start ->{
                val gotoMainIntent = Intent(applicationContext, MainActivity::class.java)
                startActivity(gotoMainIntent)
                finishAffinity()
            }
            R.id.img_input_user_info_top_back_btn -> onBackPressed()
        }

    }

    private fun showInputNameFormEvent(){
        val showInputNameForm = BindLayoutAnimation<ConstraintLayout>(
            applicationContext,
            cl_input_user_info_name,
            R.anim.spread_down
        )
        val slideDownPartForm = BindLayoutAnimation<ConstraintLayout>(
            applicationContext,
            cl_input_user_info_part,
            R.anim.slide_down_10_per
        )
        slideDownPartForm.startLayoutAnimation()
        showInputNameForm.startLayoutAnimation()
        cl_input_user_info_name.visibility = View.VISIBLE
        focusEditTextEvent(et_input_user_name,btn_input_user_info_end_input_part,btn_input_user_info_real_start)
    }


}