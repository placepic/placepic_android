package place.pic.ui.tag

//시스템 R파일이 import 되지않도록 주의하기
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_keyword_tag.*
import place.pic.R
import place.pic.RetrofitService

/**
 * Created By kimdahyee
 * on 7월 06일, 2020
 */

class KeywordTagActivity : AppCompatActivity() {

    val retrofitService = RetrofitService
    private val chipList = mutableListOf<Chip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_tag)

        val restaurantKeywordTagList
                = arrayOf("조용한", "갬성있는", "공부하기좋은", "뷰가좋은", "커피가맛있는", "의자가편한")

        val chipGroup = chipgroup_keyword_tag
        for (tags in restaurantKeywordTagList) {
            val chip = KeywordChipFactory.newInstance(layoutInflater) //object method 호출하기
            chipList.add(chip)
            chip.isClickable = true
            chip.text = tags
            chipGroup.addView(chip)
        }

        for (i in 0 until chipList.size) { //chipList.forEach
            chipList[i].setOnClickListener{view ->
                if (checkChip()) {//true
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

    private fun checkChip(): Boolean {
        var checkResult = false
        for (i in 0 until chipList.size) {
            if (chipList[i].isChecked) //true
                checkResult = true
        }
        return checkResult
    }

    private fun init() {
        tv_tag_save.setTextColor(Color.parseColor("#F1F4F5"))
        keyword_tag_save.setBackgroundResource(R.drawable.rectangle_gray_f1f4f5)
        keyword_tag_save.isClickable = false
    }

    fun onClick(view: View) {

    }
}

