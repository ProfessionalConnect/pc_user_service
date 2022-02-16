package com.pro.user.service.implement

import com.pro.user.domain.UserRole
import com.pro.user.dto.*
import com.pro.user.exception.custom.NotFoundEmailException
import com.pro.user.exception.custom.NotFoundPasswordException
import com.pro.user.exception.custom.NotFoundUserException
import com.pro.user.repository.UserRepository
import com.pro.user.security.JwtTokenProvider
import com.pro.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Minky on 2022-02-08
 */

@Service
class UserServiceImplement: UserService {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    override fun singIn(authenticationRequest: AuthenticationRequest): TokenResponse {
        val user = userRepository.findByEmail(authenticationRequest.email)
            .orElseThrow{ throw NotFoundEmailException() }

        if (user.matchPassword(authenticationRequest.password)) {
            return TokenResponse(
                jwtTokenProvider.createAccessToken(user.uuid, user.userRole.name),
                jwtTokenProvider.createRefreshToken(),
                user.uuid,
                user.userRole.name
            )
        }

        throw NotFoundPasswordException()
    }

    override fun singUp(userRequest: UserRequest, userRole: UserRole): Long? {
        val user = userRepository.save(userRequest.toEntity(userRole))
        return user.id
    }

    override fun refresh(refreshRequest: RefreshRequest): TokenResponse {
        jwtTokenProvider.validateToken(refreshRequest.refreshToken)
        jwtTokenProvider.validateTokenExceptExpired(refreshRequest.accessToken)

        val uuid = jwtTokenProvider.getUserUuid(refreshRequest.accessToken)
        val user = userRepository.findByUuid(uuid).orElseThrow{ throw NotFoundUserException() }

        return TokenResponse(
            jwtTokenProvider.createAccessToken(user.uuid, user.userRole.name),
            jwtTokenProvider.createRefreshToken(),
            user.uuid,
            user.userRole.name
        )
    }

    override fun getUsersByUuid(detailRequests: List<DetailRequest>): List<DetailResponse> =
        DetailResponse.listOf(userRepository.findByUuidIn(
            detailRequests.map(DetailRequest::of)))
}