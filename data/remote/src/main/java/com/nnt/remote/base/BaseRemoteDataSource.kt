package com.nnt.remote.base

import arrow.core.Either
import com.nnt.repository.model.ErrorResponse
import java.lang.Exception

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> T): Either<ErrorResponse, T> {
        return try {
            Either.Right(call.invoke())
        } catch (e: Exception) {
            Either.Left(ErrorResponse(e.message))
        }

    }
}