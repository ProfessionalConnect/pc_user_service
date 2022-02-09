package com.pro.user.security

import com.pro.user.exception.custom.ExpiredTokenException
import com.pro.user.exception.custom.InvalidTokenException
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

/**
 * Created by Minky on 2022-02-08
 */

@Component
class JwtTokenProvider {
    @Value("spring.jwt.secret")
    private lateinit var secretKey: String

    private val accessTokenValidSecond = 1000L * 60 * 60
    private val refreshTokenValidSecond = 1000L * 60 * 60 * 24

    @PostConstruct
    private fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createAccessToken(uuid: String, userRole: String): String {
        val claims = Jwts.claims().setSubject(uuid)
        claims["role"] = userRole
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + accessTokenValidSecond))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun createRefreshToken(): String {
        val now = Date()
        return Jwts.builder()
            .setIssuedAt(now) // 토큰 발행일자
            .setExpiration(Date(now.time + refreshTokenValidSecond)) // set Expire Time
            .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
            .compact()
    }

    fun getUserUuid(token: String): String {
        return Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token).body.subject
    }

    fun getUserRole(token: String): String {
        return Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token).body["role"].toString()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
        } catch (e: SecurityException) {
            throw InvalidTokenException()
        } catch (e: MalformedJwtException) {
            throw InvalidTokenException()
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: UnsupportedJwtException) {
            throw InvalidTokenException()
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException()
        }
        return true
    }

    fun validateTokenExceptExpired(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
        } catch (e: SecurityException) {
            throw InvalidTokenException()
        } catch (e: MalformedJwtException) {
            throw InvalidTokenException()
        } catch (e: ExpiredJwtException) {
        } catch (e: UnsupportedJwtException) {
            throw InvalidTokenException()
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException()
        }
        return true
    }
}