package place.pic.data.remote.response

import place.pic.ui.util.unixDateTimeParser

data class CommentResponse(
    val user: User,
    val comment: Comment,
) {
    fun postCountAndCreateAt():String =
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
    val subComments: List<SubComment?>,
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

fun mockCommentList() = listOf<CommentResponse>(
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박박박"),
        comment = mockComment(true)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박박박"),
        comment = mockComment(true)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박박박"),
        comment = mockComment(true)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박박박"),
        comment = mockComment(true)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    ),
    CommentResponse(
        user = mockCommentUser("박진수"),
        comment = mockComment(false)
    )
)

fun mockCommentUser(name: String) = User(
    userIdx = 1,
    postCount = 10,
    profileImageUrl = "https://sopt26.s3.ap-northeast-2.amazonaws.com/images/origin/1604347672598.jpg",
    userName = name
)

fun mockComment(hasSubComment: Boolean) = Comment(
    commentIdx = 1,
    content = "댓글 테스트",
    createdAt = 1614862457,
    updatedAt = 1614862457,
    subComments = if (hasSubComment) listOf() else listOf(mockSubComment(), mockSubComment())
)

fun mockSubComment() = SubComment(
    user = mockCommentUser("박진수"),
    comment = mockSubCommentElement()
)

fun mockSubCommentElement() = SubCommentElement(
    commentIdx = 1,
    content = "댓글 테스트",
    createdAt = 1614862457,
    updatedAt = 1614862457,
)
