package com.pro.user.dto

import com.pro.user.domain.User


/**
 * Created by Minky on 2022-02-08
 */
data class UserResponse(
    val nickname: String,
    val college: String,
    val rectal: String,
    val description: String,
) {
    companion object {
        fun of(user: User): UserResponse =
            UserResponse(
                user.nickname,
                user.college,
                user.rectal,
                user.description
            )

        fun listOf(userList: List<User>): List<UserResponse> =
            userList.map(UserResponse::of)
    }
}