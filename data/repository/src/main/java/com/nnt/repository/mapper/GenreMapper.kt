package com.nnt.repository.mapper

import com.nnt.domain.model.GenreModel
import com.nnt.remote.response.Genre

class GenreMapper: Mapper<Genre, GenreModel>() {
    override fun map(input: Genre): GenreModel {
        return  GenreModel(id = input.id, name = input.name)
    }
}