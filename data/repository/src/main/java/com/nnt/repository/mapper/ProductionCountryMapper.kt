package com.nnt.repository.mapper

import com.nnt.domain.model.ProductionCountryModel
import com.nnt.remote.response.ProductionCountry

class ProductionCountryMapper: Mapper<ProductionCountry, ProductionCountryModel>() {
    override fun map(input: ProductionCountry): ProductionCountryModel {
        return ProductionCountryModel(iso_3166_1 = input.iso_3166_1, name = input.name)
    }
}