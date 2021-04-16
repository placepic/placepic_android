package place.pic.data.remote.response

import place.pic.ui.util.unixDateTimeParser

data class CommentResponse(
    val user: User,
    val comment: Comment,
) {
    fun postCountAndCreateAt(): String =
        "작성한글 ${user.postCount} · ${unixDateTimeParser(comment.updatedAt.toLong())}2021-00-00"
}

data class User(
    val postCount: Int,
    val profileImageUrl: String,
    val userIdx: Int,
    val userName: String
)

data class Comment(
    val commentIdx: Int,
    val content: String,
    val createdAt: Int,
    val subComments: List<SubComment> = listOf(),
    val deleteBtn: Boolean,
    val updatedAt: Int
)

data class SubComment(
    val user: User,
    val comment: SubCommentElement,
) {
    fun postCountAndCreateAt(): String =
        "작성한글 ${user.postCount} · ${unixDateTimeParser(comment.updatedAt.toLong())}2021-00-00"
}

data class SubCommentElement(
    val commentIdx: Int,
    val content: String,
    val createdAt: Int,
    val updatedAt: Int
)
