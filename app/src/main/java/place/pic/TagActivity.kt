package place.pic

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.activity_tag.*
import java.text.ChoiceFormat


class TagActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)

        //ChipDrawable.
        //val chip = Chip(this).setChipDrawable()

        val resturantTagList = arrayOf("조용한", "갬성있는", "공부하기좋은", "뷰가좋은", "커피가맛있는", "의자가편한")

        val chipGroup = chipgroup_tag
        for (tag in resturantTagList) {

            val chip = Chip(this)
            chip.isClickable = true
            chip.text = tag
            chipGroup.addView(chip)
        }
    }

    fun onClick(view: View) {}

}