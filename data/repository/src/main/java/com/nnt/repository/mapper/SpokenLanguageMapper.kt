package com.nnt.repository.mapper

import com.nnt.domain.model.SpokenLanguageModel
import com.nnt.remote.response.SpokenLanguage

class SpokenLanguageMapper: Mapper<SpokenLanguage, SpokenLanguageModel>() {
    override fun map(input: SpokenLanguage): SpokenLanguageModel {
        return SpokenLanguageModel(iso_639_1 = input.iso_639_1, name = input.name)
    }
}