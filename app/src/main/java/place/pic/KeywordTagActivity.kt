package place.pic

//시스템 R파일이 import 되지않도록 주의하기
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_keyword_tag.*

/**
 * Created By kimdahyee
 * on 7월 06일, 2020
 */

class KeywordTagActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_tag)

        val resturantKeywordTagList = arrayOf("조용한", "갬성있는", "공부하기좋은", "뷰가좋은", "커피가맛있는", "의자가편한")

        val chipGroup = chipgroup_keyword_tag
        for (tags in resturantKeywordTagList) {
            val chip = FactoryKeywordChip.newInstance(layoutInflater) //object method 호출하기
            chip.isClickable = true
            chip.text = tags
            chipGroup.addView(chip)
        }
    }

    fun onClick(view: View) {
    }

}