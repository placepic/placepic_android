package place.pic

//시스템 R파일이 import 되지않도록 주의하기
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_tag.*


class TagActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)

        //ChipDrawable.
        //val chip = Chip(this).setChipDrawable()

        val resturantTagList = arrayOf("조용한", "갬성있는", "공부하기좋은", "뷰가좋은", "커피가맛있는", "의자가편한")

        val chipGroup = chipgroup_tag
        for (tag in resturantTagList) {

            val chip = layoutInflater.inflate(R.layout.chip_tag, null, false) as Chip
            chip.isClickable = true
            chip.text = tag
            chipGroup.addView(chip)
        }
    }
    
    //객체를 생성해서 

    fun onClick(view: View) {
        //KeywordChipViewCreator.create()
        //객체를 생성하지않고 함수 호출만으로도 칩을 생성할 수 있도록 유틸성 method 만들어보기
    }

}