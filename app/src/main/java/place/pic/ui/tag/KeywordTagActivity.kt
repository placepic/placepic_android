package place.pic.ui.tag

//시스템 R파일이 import 되지않도록 주의하기
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_keyword_tag.*
import place.pic.R

/**
 * Created By kimdahyee
 * on 7월 06일, 2020
 */

class KeywordTagActivity : AppCompatActivity() {

    private val keywordTagChipList = mutableListOf<Chip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_tag)

        val restaurantKeywordTagList
                = arrayOf("조용한", "갬성있는", "공부하기좋은", "뷰가좋은", "커피가맛있는", "의자가편한")

        val chipGroup = chipgroup_keyword_tag
        for (tags in restaurantKeywordTagList) {
            val chip = KeywordChipFactory.newInstance(layoutInflater) //object method 호출하기
            keywordTagChipList.add(chip)
            chip.isClickable = true
            chip.text = tags
            chipGroup.addView(chip)
        }

        for (i in 0 until keywordTagChipList.size) { //chipList.forEach로도 가능
            keywordTagChipList[i].setOnClickListener{view ->
                if (checkChipIsChecked()) {//true
                    tv_tag_save.setTextColor(Color.parseColor("#FFFFFF"))
                    keyword_tag_save.setBackgroundResource(R.drawable.rectangle_main_color)
                    keyword_tag_save.isClickable = true
                } else {
                    tv_tag_save.setTextColor(Color.parseColor("#4F4F4F"))
                    keyword_tag_save.setBackgroundResource(R.drawable.rectangle_gray_f1f4f5)
                    keyword_tag_save.isClickable = false
                }
            }
        }
    }

    private fun checkChipIsChecked(): Boolean {
        var checkResult = false
        for (i in 0 until keywordTagChipList.size) {
            if (keywordTagChipList[i].isChecked) //true
                checkResult = true
        }
        return checkResult
    }

    fun onClick(view: View) {
        Toast.makeText(this@KeywordTagActivity, "클릭 가능", Toast.LENGTH_SHORT).show()
    }
}

