package place.pic.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_splash.*
import place.pic.R
import place.pic.ui.login.LoginPageActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        lottie.addAnimatorListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(this@SplashActivity, LoginPageActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }
        })
        lottie.playAnimation()

    }


}