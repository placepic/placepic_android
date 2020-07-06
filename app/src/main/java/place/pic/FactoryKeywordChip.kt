package place.pic

import android.view.LayoutInflater
import com.google.android.material.chip.Chip

class FactoryKeywordChip {

    //객체를 생성하지않고 함수 호출만으로도 칩을 생성할 수 있도록 유틸성 method 만들어보기
    //factory pattern
    //factory.keyword.chip
    //newInstance()
    companion object {
        fun newInstance(layoutInflater : LayoutInflater) : Chip {
            val chip = layoutInflater.inflate(R.layout.chip_tag, null, false) as Chip
            return chip
        }
    }

}