package com.barzabaldevs.mobilechallengeuala.domain.model

import com.barzabaldevs.mobilechallengeuala.data.database.entities.CountryEntity

data class CountryModel(
    val id: Int,
    val country: String,
    val name: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toDataBase(): CountryEntity = CountryEntity(
        id = id,
        country = country,
        name = name,
        latitude = latitude,
        longitude = longitude
    )
}
