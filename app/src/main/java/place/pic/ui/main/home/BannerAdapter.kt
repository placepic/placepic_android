package place.pic.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import place.pic.R

/**
 * Created By kimdahyee
 * on 09월 12일, 2020
 */

class BannerAdapter : PagerAdapter() {

    private val Images = arrayOf(
        R.drawable.testphoto,
        R.drawable.testphoto1,
        R.drawable.testphoto2
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        //페이지뷰가 키 객체와 연관되는지 확인
        return view === `object`
    }

    override fun getCount(): Int {
        //뷰페이저의 전체 페이지 수 결정
        return Integer.MAX_VALUE
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        //화면에 표시할 페이지뷰 생성

        val realPos = position % Images.size

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner_list, parent, false)
        val image = view.findViewById<View>(R.id.img_banner_list) as ImageView

        image.setImageResource(Images[realPos])
        val vp = parent as ViewPager
        vp.addView(view, 0)

        //image.setOnClickListener{}

        return view
    }

    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        val vp = parent as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}