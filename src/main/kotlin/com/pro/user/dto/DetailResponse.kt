package com.pro.user.dto

import com.pro.user.domain.User


/**
 * Created by Minky on 2022-02-08
 */
data class DetailResponse(
    val uuid: String,
    val nickname: String,
    val college: String,
    val rectal: String,
    val description: String,
) {
    companion object {
        fun of(user: User): DetailResponse =
            DetailResponse(
                user.uuid,
                user.nickname,
                user.college,
                user.rectal,
                user.description
            )

        fun listOf(userList: List<User>): List<DetailResponse> =
            userList.map(DetailResponse::of)
    }
}