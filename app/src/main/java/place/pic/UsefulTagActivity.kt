package place.pic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_useful_tag.*

/**
 * Created By kimdahyee
 * on 7월 07일, 2020
 */

class UsefulTagActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useful_tag)

        val resturantUsefulTagList = arrayOf("24시간", "콘센트있음", "화장실공용")

        val chipGroup = chipgroup_useful_tag
        for (tags in resturantUsefulTagList) {
            val chip = FactoryKeywordChip.newInstance(layoutInflater) //object method 호출하기
            chip.isClickable = true
            chip.text = tags
            chipGroup.addView(chip)
        }
    }

    fun onClick(view: View) {
    }
}