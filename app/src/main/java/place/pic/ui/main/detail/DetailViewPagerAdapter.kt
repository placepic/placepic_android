package place.pic.ui.main.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import place.pic.R

class DetailViewPagerAdapter(
    private val context : Context,
    private val Image: List<String>
) : PagerAdapter() {

    private var layoutInflater : LayoutInflater? = null


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view ===  `object`
    }

    override fun getCount(): Int {
        return Image.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.item_image_page, null)
        val image = v.findViewById<View>(R.id.img_viewpager_image) as ImageView

        Glide.with(container).load(Image[position]).into(image)

        val viewPager = container as ViewPager
        viewPager.addView(v , 0)

        return v
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}