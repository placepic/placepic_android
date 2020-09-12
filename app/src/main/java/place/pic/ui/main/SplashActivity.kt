package place.pic.ui.main

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import place.pic.R
import place.pic.ui.util.animation.BindLayoutAnimation


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        showSplashTextAnimation()
    }


    private fun showSplashTextAnimation() {
        firstSplashAnimationEvent()
        secondSplashAnimationEvent()
        lastSplashAnimationEvent()
    }

    private fun firstSplashAnimationEvent(){
        val firstSplashAnimation = BindLayoutAnimation<TextView>(
            applicationContext,
            tv_splash_text1,
            R.anim.alpha_splash_text_in
        )
        firstSplashAnimation.startLayoutAnimation()
    }

    private fun secondSplashAnimationEvent(){
        val secondSplashAnimation = BindLayoutAnimation<TextView>(
            applicationContext,
            tv_splash_text2,
            R.anim.alpha_splash_text_in
        )
        secondSplashAnimation.setStartOffsetInAnimation(400)
        secondSplashAnimation.startLayoutAnimation()
    }

    private fun lastSplashAnimationEvent(){
        val lastSplashAnimation = BindLayoutAnimation<TextView>(
            applicationContext,
            tv_splash_text3,
            R.anim.alpha_splash_text_in
        )
        lastSplashAnimation.setStartOffsetInAnimation(800)
        lastSplashAnimation.setAnimationListener(
            onAnimationEndListener = {endSplashActivityEvent()}
        )
        lastSplashAnimation.startLayoutAnimation()
    }

    private fun endSplashActivityEvent(){
        val gotoOnBoardingIntent = Intent(applicationContext, OnBoardingActivity::class.java)
        startActivity(gotoOnBoardingIntent)
        overridePendingTransition(R.anim.load_fade_in,R.anim.alpah_slide_out)
        finish()
    }

}