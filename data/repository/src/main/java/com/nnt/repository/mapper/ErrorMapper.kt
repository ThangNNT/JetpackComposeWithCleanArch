package com.nnt.repository.mapper

import com.nnt.domain.model.ErrorModel
import com.nnt.repository.model.ErrorResponse

class ErrorMapper: Mapper<ErrorResponse, ErrorModel>() {
    override fun map(input: ErrorResponse): ErrorModel {
        return ErrorModel(input.message)
    }
}