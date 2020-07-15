package place.pic.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import place.pic.R
import place.pic.ui.group.waitgrouplist.WaitGroupActivity
import place.pic.ui.login.LoginPageActivity
import place.pic.ui.main.MainActivity


class SplashActivity : AppCompatActivity() {
    var anim: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        anim = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.anim_splash_word
        ) // Create the animation.

        anim!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                val intent  = Intent(this@SplashActivity,LoginPageActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        splash_text.startAnimation(anim)
    }
}