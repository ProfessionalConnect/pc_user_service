package com.pro.user.dto

import com.pro.user.domain.User
import com.pro.user.domain.UserRole
import com.pro.user.security.SaltBuilder
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Minky on 2022-02-08
 */
data class UserRequest(
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email format is wrong")
    @Size(min = 2, message = "Email must be longer than two characters")
    val email: String,

    @NotNull(message = "Nickname cannot be null")
    @Size(min = 2, message = "Nickname must be longer than two characters")
    val nickname: String,

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be longer than eight characters")
    val password: String,

    @NotNull(message = "College cannot be null")
    val college: String,

    @NotNull(message = "Rectal cannot be null")
    val rectal: String,

    @NotNull(message = "Description cannot be null")
    val description: String,
) {
    fun toEntity(userRole: UserRole): User =
        User(email, nickname, password, SaltBuilder.getSalt(), userRole, college, rectal, description)
}