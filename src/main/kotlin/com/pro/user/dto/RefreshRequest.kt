package com.pro.user.dto

import javax.validation.constraints.NotNull

/**
 * Created by Minky on 2022-02-09
 */
data class RefreshRequest(
    @NotNull(message = "AccessToken cannot be null")
    val accessToken: String,

    @NotNull(message = "RefreshToken cannot be null")
    val refreshToken: String,
) {
}