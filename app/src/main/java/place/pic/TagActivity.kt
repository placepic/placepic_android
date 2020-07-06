package place.pic

//시스템 R파일이 import 되지않도록 주의하기
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_tag.*


class TagActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)

        val resturantTagList = arrayOf("조용한", "갬성있는", "공부하기좋은", "뷰가좋은", "커피가맛있는", "의자가편한")

        val chipGroup = chipgroup_tag
        for (tags in resturantTagList) {
            val chip = FactoryKeywordChip.newInstance(layoutInflater) //object method 호출하기
            chip.isClickable = true
            chip.text = tags
            chipGroup.addView(chip)
        }

    }

    fun onClick(view: View) {

    }

}