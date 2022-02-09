package com.pro.user.security

import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * Created by Minky on 2022-02-09
 */

object SaltBuilder {
    fun encodePassword(password: String, salt: String) =
        BCrypt.hashpw(password, salt)

    fun getSalt() =
        BCrypt.gensalt()
}