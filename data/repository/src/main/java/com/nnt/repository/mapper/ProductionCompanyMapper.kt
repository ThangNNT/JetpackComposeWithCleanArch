package com.nnt.repository.mapper

import com.nnt.domain.model.ProductionCompanyModel
import com.nnt.remote.response.ProductionCompany

class ProductionCompanyMapper: Mapper<ProductionCompany, ProductionCompanyModel>(){
    override fun map(input: ProductionCompany): ProductionCompanyModel {
        return  ProductionCompanyModel(
            id = input.id,
            name = input.name,
            logo_path = input.logo_path,
            origin_country = input.origin_country
        )
    }
}