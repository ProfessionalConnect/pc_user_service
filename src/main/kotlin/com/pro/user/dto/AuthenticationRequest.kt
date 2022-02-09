package com.pro.user.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Minky on 2022-02-09
 */
data class AuthenticationRequest(
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email format is wrong")
    @Size(min = 2, message = "Email must be longer than two characters")
    val email: String,

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be longer than eight characters")
    val password: String,
) {
}