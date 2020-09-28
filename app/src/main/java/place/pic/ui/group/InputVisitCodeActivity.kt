package place.pic.ui.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_input_visit_code.*
import place.pic.R
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.animation.nextActivityAnimation
import place.pic.ui.util.animation.previousActivityAnimation
import place.pic.ui.util.customTextChangedListener

class InputVisitCodeActivity : AppCompatActivity() {

    private var groupCode:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_visit_code)
        groupCode = intent.getStringExtra("groupCode")!!
        init()
    }

    private fun init() {
        initAnimation()
        editTextChangeEventMapping()
        buttonEventMapping()
    }

    private fun initAnimation(){
        val inputVisitCodeFormAnim = BindLayoutAnimation<ConstraintLayout>(
            applicationContext,
            cl_input_visit_code_group_view,
            R.anim.spread_down
        )
        inputVisitCodeFormAnim.setStartOffsetInAnimation(700)
        inputVisitCodeFormAnim.startLayoutAnimation()
    }

    private fun buttonEventMapping() {
        btn_into_group.setOnClickListener {groupCodeInputAndClickButtonEvent()}
        img_input_visit_code_top_back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun groupCodeInputAndClickButtonEvent(){
        if (et_input_visit_code.text.toString() == groupCode) {
            intoGroupEvent()
            return
        }
        notMatchGroupCode()
    }

    private fun intoGroupEvent(){
        //TODO: 서버 연결로직(초대 코드 입력)
        val gotoInputUserInfoIntent = Intent(applicationContext, InputUserInfoAnimActivity::class.java)
        startActivity(gotoInputUserInfoIntent)
        nextActivityAnimation()
    }

    private fun notMatchGroupCode(){
        Toast.makeText(applicationContext, "그룹코드가 일치하지 않습니다!!", Toast.LENGTH_SHORT)
            .show()
    }

    private fun editTextChangeEventMapping() {
        et_input_visit_code.customTextChangedListener {
            btn_into_group.isEnabled = !it.isNullOrBlank()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        previousActivityAnimation()
    }
}