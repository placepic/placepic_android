package place.pic.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import place.pic.R
import place.pic.data.PlacepicAuthRepository
import place.pic.ui.group.GroupListActivity
import place.pic.ui.util.animation.BindLayoutAnimation
import place.pic.ui.util.animation.nextActivityAnimation


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        showSplashTextAnimation()
    }


    private fun showSplashTextAnimation() {
        firstSplashAnimationEvent()
        secondSplashAnimationEvent()
        lastSplashAnimationEvent()
    }

    private fun firstSplashAnimationEvent() {
        val firstSplashAnimation = BindLayoutAnimation<TextView>(
            applicationContext,
            tv_splash_text1,
            R.anim.alpha_splash_text_in
        )
        firstSplashAnimation.startLayoutAnimation()
    }

    private fun secondSplashAnimationEvent() {
        val secondSplashAnimation = BindLayoutAnimation<TextView>(
            applicationContext,
            tv_splash_text2,
            R.anim.alpha_splash_text_in
        )
        secondSplashAnimation.setStartOffsetInAnimation(400)
        secondSplashAnimation.startLayoutAnimation()
    }

    private fun lastSplashAnimationEvent() {
        val lastSplashAnimation = BindLayoutAnimation<TextView>(
            applicationContext,
            tv_splash_text3,
            R.anim.alpha_splash_text_in
        )
        lastSplashAnimation.setStartOffsetInAnimation(800)
        lastSplashAnimation.setAnimationListener(
            onAnimationEndListener = { endSplashActivityEvent() }
        )
        lastSplashAnimation.startLayoutAnimation()
    }

    private fun endSplashActivityEvent() {
        if (PlacepicAuthRepository.getInstance(applicationContext).userToken != null) {
            haveTokenEvent()
            return
        }
        gotoOnboardingEventWithoutToken()
    }

    private fun haveTokenEvent() {
        if (PlacepicAuthRepository.getInstance(applicationContext).groupId != 0) {
            gotoMainEvent()
            return
        }
        gotoSelectGroupEvent()
    }

    private fun gotoOnboardingEventWithoutToken() {
        Log.d(
            "TokenTest",
            PlacepicAuthRepository.getInstance(applicationContext).userToken ?: "null"
        )
        changeActivityAndFinishEvent(OnBoardingActivity::class.java)
    }

    private fun gotoSelectGroupEvent(){
        Log.d(
            "GroupIdTest",
            PlacepicAuthRepository.getInstance(applicationContext).groupId.toString()
        )
        changeActivityAndFinishEvent(GroupListActivity::class.java)
    }

    private fun gotoMainEvent() {
        Log.d(
            "TokenTest",
            PlacepicAuthRepository.getInstance(applicationContext).userToken ?: "null"
        )
        changeActivityAndFinishEvent(MainActivity::class.java)
    }
    private fun changeActivityAndFinishEvent(activityClass: Class<*>) {
        val activityChangeIntent = Intent(applicationContext, activityClass)
        startActivity(activityChangeIntent)
        finish()
    }

    override fun finish() {
        super.finish()
        nextActivityAnimation()
    }

}