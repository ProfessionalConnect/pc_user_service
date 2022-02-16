package com.pro.user.service

import com.pro.user.domain.UserRole
import com.pro.user.dto.*

/**
 * Created by Minky on 2022-02-08
 */

interface UserService {
    fun singIn(authenticationRequest: AuthenticationRequest): TokenResponse
    fun singUp(userRequest: UserRequest, userRole: UserRole): Long?
    fun refresh(refreshRequest: RefreshRequest): TokenResponse
    fun getUsersByUuid(detailRequests: List<DetailRequest>): List<DetailResponse>
}