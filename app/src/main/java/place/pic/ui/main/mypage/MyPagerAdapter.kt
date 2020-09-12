package place.pic.ui.main.mypage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import place.pic.ui.main.bookmark.BookmarksFragment

class MyPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BlankFragment()
            else -> BookmarksFragment()
        }
    }
    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "작성한 글"
            else -> "저장한 장소"
        }
    }


}