package place.pic.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_on_boarding.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.ui.login.LoginActivity
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.animation.nextActivityAnimation


/*
TODO 토큰이 있으면 바로 그룹리스트로 이동하게 하기
*/
class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        init()
    }

    private fun init(){
        setUpWindowAnimations()
        setButtonEvent()
    }

    private fun setUpWindowAnimations() {
        val imageAnimation = BindLayoutAnimation<ImageView>(
            applicationContext,
            img_onboarding_img,
            R.anim.load_fade_in
        )
        imageAnimation.startLayoutAnimation()
    }

    private fun setButtonEvent(){
        btn_onboarding_start_place_pic.setOnClickListener {
            val gotoLoginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(gotoLoginIntent)
            finish()
            nextActivityAnimation()
        }
    }

}