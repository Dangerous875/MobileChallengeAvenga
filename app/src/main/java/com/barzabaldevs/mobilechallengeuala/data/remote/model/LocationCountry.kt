package com.barzabaldevs.mobilechallengeuala.data.remote.model

import com.google.gson.annotations.SerializedName

data class LocationCountry(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
