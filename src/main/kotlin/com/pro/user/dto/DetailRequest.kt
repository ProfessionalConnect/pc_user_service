package com.pro.user.dto

import javax.validation.constraints.NotNull

/**
 * Created by Minky on 2022-02-17
 */
data class DetailRequest(
    @NotNull(message = "UUID cannot be null")
    val uuid: String,
) {
    companion object {
        fun of(detailRequest: DetailRequest): String =
            detailRequest.uuid
    }
}