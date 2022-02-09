package com.pro.user.domain

import com.pro.user.security.SaltBuilder
import java.util.*
import javax.persistence.*

/**
 * Created by Minky on 2022-02-08
 */

@Entity
@Table(
    name = "user_tbl",
    uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "email", "uuid"])]
)
class User(
    email: String,
    nickname: String,
    password: String,
    salt: String,
    userRole: UserRole,
    college: String,
    rectal: String,
    description: String,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_gen")
    @SequenceGenerator(name = "user_sequence_gen", sequenceName = "user_sequence")
    @Column(name = "user_id", nullable = false)
    var id: Long? = null

    @Column(nullable = false, unique = true, length = 100)
    val email = email

    @Column(nullable = false)
    val encryptPassword = SaltBuilder.encodePassword(password, salt)

    @Column(nullable = false)
    val salt = salt

    @Column(nullable = false)
    val nickname = nickname

    @Column(nullable = false, unique = true)
    val uuid = UUID.randomUUID().toString()

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val userRole = userRole

    @Column(nullable = false)
    val college = college

    @Column(nullable = false)
    val rectal = rectal

    @Column(nullable = false)
    val description = description

    fun matchPassword(password: String) =
        encryptPassword == SaltBuilder.encodePassword(password, salt)
}