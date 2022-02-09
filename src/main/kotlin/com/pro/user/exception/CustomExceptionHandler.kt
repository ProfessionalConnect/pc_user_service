package com.pro.user.exception

import com.pro.user.exception.custom.*
import com.pro.user.exception.message.ErrorBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Created by Minky on 2022-02-09
 */

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(CustomException::class)
    @ResponseBody
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorBody> =
        ResponseEntity(getErrorBody(e), getHttpStatus(e))

    @ExceptionHandler(ExpiredTokenException::class)
    @ResponseBody
    fun handleExpiredTokenException(e: ExpiredTokenException): ResponseEntity<ErrorBody> =
        ResponseEntity(getErrorBody(e), getHttpStatus(e))

    @ExceptionHandler(InvalidEmailOrPasswordException::class)
    @ResponseBody
    fun handleInvalidEmailOrPasswordException(e: InvalidEmailOrPasswordException): ResponseEntity<ErrorBody> =
        ResponseEntity(getErrorBody(e), getHttpStatus(e))

    @ExceptionHandler(InvalidTokenException::class)
    @ResponseBody
    fun handleInvalidTokenException(e: InvalidTokenException): ResponseEntity<ErrorBody> =
        ResponseEntity(getErrorBody(e), getHttpStatus(e))

    @ExceptionHandler(NotFoundEmailException::class)
    @ResponseBody
    fun handleNotFoundEmailException(e: NotFoundEmailException): ResponseEntity<ErrorBody> =
        ResponseEntity(getErrorBody(e), getHttpStatus(e))

    @ExceptionHandler(NotFoundPasswordException::class)
    @ResponseBody
    fun handleNotFoundPasswordException(e: NotFoundPasswordException): ResponseEntity<ErrorBody> =
        ResponseEntity(getErrorBody(e), getHttpStatus(e))

    @ExceptionHandler(NotFoundUserException::class)
    @ResponseBody
    fun handleNotFoundUserException(e: NotFoundUserException): ResponseEntity<ErrorBody> =
        ResponseEntity(getErrorBody(e), getHttpStatus(e))

    private fun getHttpStatus(e: CustomException): HttpStatus =
        e.errorCode.httpStatus

    private fun getErrorBody(e: CustomException): ErrorBody {
        val errorCode = e.errorCode
        return ErrorBody(errorCode.code, errorCode.message, errorCode.httpStatus)
    }
}