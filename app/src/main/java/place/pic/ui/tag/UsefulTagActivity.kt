package place.pic.ui.tag

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_useful_tag.*
import place.pic.R

/**
 * Created By kimdahyee
 * on 7월 07일, 2020
 */

class UsefulTagActivity : AppCompatActivity() {

    private val usefulTagChipList = mutableListOf<Chip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useful_tag)

        val resturantUsefulTagList
                = arrayOf("24시간", "콘센트있음", "화장실공용")

        val chipGroup = chipgroup_useful_tag
        for (tags in resturantUsefulTagList) {
            val chip = KeywordChipFactory.newInstance(layoutInflater) //object method 호출하기
            usefulTagChipList.add(chip)
            chip.isClickable = true
            chip.text = tags
            chipGroup.addView(chip)
        }

        for (i in 0 until usefulTagChipList.size) { //chipList.forEach로도 가능
            usefulTagChipList[i].setOnClickListener{view ->
                if (checkChipIsChecked()) {//true
                    tv_useful_tag_save.setTextColor(Color.parseColor("#FFFFFF"))
                    useful_tag_save.setBackgroundResource(R.drawable.rectangle_main_color)
                    useful_tag_save.isClickable = true
                } else {
                    tv_useful_tag_save.setTextColor(Color.parseColor("#4F4F4F"))
                    useful_tag_save.setBackgroundResource(R.drawable.rectangle_gray_f1f4f5)
                    useful_tag_save.isClickable = false
                }
            }
        }
    }

    private fun checkChipIsChecked(): Boolean {
        var checkResult = false
        for (i in 0 until usefulTagChipList.size) {
            if (usefulTagChipList[i].isChecked) //true
                checkResult = true
        }
        return checkResult
    }

    fun onClick(view: View) {
        Toast.makeText(this@UsefulTagActivity, "클릭 가능", Toast.LENGTH_SHORT).show()
    }
}