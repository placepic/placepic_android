package place.pic.ui.main.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import place.pic.data.PlacepicAuthRepository
import place.pic.data.entity.Bookmark
import place.pic.databinding.FragmentBookmarksBinding
import place.pic.ui.main.detail.DetailViewActivity


class BookmarksFragment : Fragment() {

    private lateinit var bookmarksAdapter: BookmarksAdapter
    private lateinit var bookmarksViewModel: BookmarksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        initView(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeBookmarks()
        bookmarksViewModel.requestBookmarks()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == BOOKMARK_CANCELED || resultCode == PLACE_DELETED) {
            val placeIdx = data?.getLongExtra("placeIdx", -1) ?: return
            bookmarksViewModel.removeBookmark(placeIdx)
        }
    }

    private fun initView(binding: FragmentBookmarksBinding) {
        bookmarksViewModel =
            BookmarksViewModel(PlacepicAuthRepository.getInstance(requireContext()))
        bookmarksAdapter = BookmarksAdapter()
        bookmarksAdapter.setItemClickListener { onBookmarkClick(it) }
        binding.viewModel = bookmarksViewModel
        binding.lifecycleOwner = this
        binding.rvBookmarks.adapter = bookmarksAdapter
    }

    private fun subscribeBookmarks() {
        bookmarksViewModel.bookmarks.observe(this, Observer {
            bookmarksAdapter.submitList(it)
        })
    }

    private fun onBookmarkClick(bookmark: Bookmark) {
        val intent = Intent(requireActivity(), DetailViewActivity::class.java)
        intent.putExtra("placeIdx", bookmark.placeIdx)
        startActivityForResult(intent, 5000)
    }

    companion object {
        const val PLACE_DELETED = 1
        const val BOOKMARK_CANCELED = 2
    }
}