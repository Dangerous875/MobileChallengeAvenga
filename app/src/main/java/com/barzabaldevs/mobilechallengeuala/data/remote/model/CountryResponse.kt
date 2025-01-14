package com.barzabaldevs.mobilechallengeuala.data.remote.model

import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("_id") val id: Int,
    val country: String,
    val name: String,
    @SerializedName("coord") val location: LocationCountry
) {
    fun toDomain(): CountryModel = CountryModel(
        id = id,
        country = country,
        name = name,
        latitude = location.latitude,
        longitude = location.longitude
    )
}