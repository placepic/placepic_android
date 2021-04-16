package place.pic.ui.main.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.data.remote.response.CommentResponse
import place.pic.databinding.ItemCommentParentHasRecommentBinding
import place.pic.databinding.ItemCommentParentNotHasRecommentBinding

class DetailViewCommentAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val comments = mutableListOf<CommentResponse>()

    private var popUpCommentDelEvent: ((commentIdx: Int) -> Unit)? = null
    fun setPopUpCommentDelEvent(listener: (commentIdx: Int) -> Unit) {
        this.popUpCommentDelEvent = listener
    }

    fun addAllComments(newComment: List<CommentResponse>) {
        comments.clear()
        comments.addAll(newComment)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int =
        if (comments[position].comment.subComments.isEmpty()) COMMENT_TYPE else HAS_REPLY_COMMENT_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        /*return if (viewType == HAS_REPLY_COMMENT_TYPE) {
            ItemCommentParentHasRecommentBinding
                .inflate(layoutInflater, parent, false)
                .let { CommentWithReplyViewHolder(it) }
        } else {
            ItemCommentParentNotHasRecommentBinding
                .inflate(layoutInflater, parent, false)
                .let { CommentViewHolder(it) }
        }*/
        return ItemCommentParentNotHasRecommentBinding
            .inflate(layoutInflater, parent, false)
            .let { CommentViewHolder(it) }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val commentViewHolder = holder as CommentViewHolder
        commentViewHolder.onBind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    class CommentWithReplyViewHolder(
        val binding: ItemCommentParentHasRecommentBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class CommentViewHolder(
        val binding: ItemCommentParentNotHasRecommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(commentResponse: CommentResponse) {
            binding.commentResponse = commentResponse
            binding.commentDeleteButton.setOnClickListener {
                popUpCommentDelEvent?.invoke(commentResponse.comment.commentIdx)
            }
        }
    }

    companion object {
        private const val HAS_REPLY_COMMENT_TYPE = 0
        private const val COMMENT_TYPE = 1
    }
}
