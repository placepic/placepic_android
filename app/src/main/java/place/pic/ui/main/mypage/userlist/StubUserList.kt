package place.pic.ui.main.mypage.userlist

import android.media.MediaRouter

/**
 * Created By kimdahyee
 * on 07월 14일, 2020
 */

fun stubUserList(): List<UserListData> {
    return listOf(
        UserListData(
            //img = "",
            userName = "김다혜",
            part = "다혜왕자",
            postCount = 8
        ),
        UserListData(
            //img = "",
            userName = "이연정",
            part = "아버지",
            postCount = 6
        ),
        UserListData(
            //img = "",
            userName = "조흐연",
            part = "딸",
            postCount = 5
        ),
        UserListData(
            //img = "",
            userName = "배민주",
            part = "민주주의",
            postCount = 3
        ),
        UserListData(
            //img = "",
            userName = "이수정",
            part = "공주님",
            postCount = 1
        )
    )
}