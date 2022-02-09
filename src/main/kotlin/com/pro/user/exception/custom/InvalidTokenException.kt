package com.pro.user.exception.custom

import com.pro.user.exception.message.ErrorCode

/**
 * Created by Minky on 2022-02-09
 */
class InvalidTokenException:
    CustomException(ErrorCode.INVALID_TOKEN)