package place.pic.ui.main.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import place.pic.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {

    private lateinit var bookmarksAdapter: BookmarksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentBookmarksBinding) {
        bookmarksAdapter = BookmarksAdapter()
        binding.rvBookmarks.adapter = bookmarksAdapter
    }

}