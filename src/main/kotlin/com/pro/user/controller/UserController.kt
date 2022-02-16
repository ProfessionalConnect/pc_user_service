package com.pro.user.controller

import com.pro.user.domain.UserRole
import com.pro.user.dto.*
import com.pro.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

/**
 * Created by Minky on 2022-02-08
 */

@RestController
@RequestMapping("/api/v1/users")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/ss/signup")
    fun memberSignUp(
        @RequestBody userRequest: UserRequest
    ): ResponseEntity<Void> {
        val userId = userService.singUp(userRequest, UserRole.MEMBER)
        return ResponseEntity.created(URI("/api/v1/users/${userId}")).build()
    }

    @PostMapping("/ps/signup")
    fun proSignUp(
        @RequestBody userRequest: UserRequest
    ): ResponseEntity<Void> {
        val userId = userService.singUp(userRequest, UserRole.PRO)
        return ResponseEntity.created(URI("/api/v1/users/${userId}")).build()
    }

    @PostMapping("/signin")
    fun signIn(
        @RequestBody authenticationRequest: AuthenticationRequest
    ): ResponseEntity<TokenResponse> =
        ResponseEntity.ok().body(userService.singIn(authenticationRequest))

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody refreshRequest: RefreshRequest
    ): ResponseEntity<TokenResponse> =
        ResponseEntity.ok().body(userService.refresh(refreshRequest))


    @PostMapping("/details")
    fun getUsersByUuid(
        @RequestBody detailRequests: List<DetailRequest>
    ): ResponseEntity<List<DetailResponse>> =
        ResponseEntity.ok().body(userService.getUsersByUuid(detailRequests))
}