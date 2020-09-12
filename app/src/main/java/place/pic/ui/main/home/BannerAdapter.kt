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
        return view === `object`
    }

    override fun getCount(): Int {
        return Images.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)
        val inflater = LayoutInflater.from(parent.context)
        val image = view.findViewById<View>(R.id.img_home_viewpager) as ImageView

        image.setImageResource(Images[position])
        val vp = parent as ViewPager
        vp.addView(view, 0)

        return view
    }

    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        val vp = parent as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}