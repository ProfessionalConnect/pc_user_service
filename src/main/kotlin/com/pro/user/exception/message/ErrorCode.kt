package com.pro.user.exception.message

import org.springframework.http.HttpStatus

/**
 * Created by Minky on 2022-02-09
 */
enum class ErrorCode(
    val code: Int,
    val message: String,
    val httpStatus: HttpStatus
) {
    INVALID_TOKEN(401, "[ERROR] Token is not Invalid, Please Check tour token.", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN(401, "[ERROR] Token is Expired, Please Refreshing token.", HttpStatus.UNAUTHORIZED),
    INVALID_EMAIL_OR_PASSWORD(
        401,
        "[ERROR] Email or Password, Please check your email or password.",
        HttpStatus.UNAUTHORIZED
    ),
    NOT_FOUND_EMAIL(401, "[ERROR] Email is not found, Please check your email.", HttpStatus.UNAUTHORIZED),
    NOT_FOUND_PASSWORD(401, "[ERROR] Password is not found, Please check your Password.", HttpStatus.UNAUTHORIZED),
    NOT_FOUND_USER(404, "[ERROR] User is not found", HttpStatus.NOT_FOUND), ;
}