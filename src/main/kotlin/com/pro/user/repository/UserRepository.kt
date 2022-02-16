package com.pro.user.repository

import com.pro.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Created by Minky on 2022-02-08
 */

@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findByEmail(email: String): Optional<User>
    fun findByUuid(uuid: String): Optional<User>
    fun findByUuidIn(uuidList: List<String>): List<User>
}