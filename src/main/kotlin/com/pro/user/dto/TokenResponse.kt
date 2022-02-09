package com.pro.user.dto

/**
 * Created by Minky on 2022-02-08
 */
data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val uuid: String,
) {
}