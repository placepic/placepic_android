package place.pic.ui.util.animation

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.dynamicanimation.animation.DynamicAnimation
import place.pic.R

class BindLayoutAnimation<T:View>(
    context: Context,
    private var layout: T,
    animationID: Int
) {
    private var bindLayoutAnimation: Animation = AnimationUtils.loadAnimation(context,animationID)
    private var animationListener:Animation.AnimationListener?=null

    fun setStartOffsetInAnimation(time:Long){
        bindLayoutAnimation.startOffset = time
    }

    fun startLayoutAnimation() {
        bindLayoutAnimation.setAnimationListener(animationListener)
        layout.startAnimation(bindLayoutAnimation)
    }

    fun setAnimationListener(
        onAnimationStartListener:()->Unit = {},
        onAnimationEndListener: ()->Unit = {},
        onAnimationRepeatListener:()->Unit = {}
    ){
        this.animationListener = object:Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) = onAnimationStartListener()

            override fun onAnimationEnd(animation: Animation?) = onAnimationEndListener()

            override fun onAnimationRepeat(animation: Animation?) = onAnimationRepeatListener()
        }
    }
}

