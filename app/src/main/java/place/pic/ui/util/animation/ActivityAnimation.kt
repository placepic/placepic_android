package place.pic.ui.util.animation

import android.app.Activity
import place.pic.R

fun Activity.nextActivityAnimation(){
    this.overridePendingTransition(
        R.anim.Activity_load_fade_in,
        R.anim.alpah_slide_out_right_to_left
    )
}

fun Activity.previousActivityAnimation(){
    this.overridePendingTransition(
        R.anim.alpah_slide_in_left_to_right,
        R.anim.Activity_load_fade_out
    )
}