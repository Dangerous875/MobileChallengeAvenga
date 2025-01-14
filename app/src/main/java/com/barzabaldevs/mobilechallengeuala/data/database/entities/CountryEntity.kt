package com.barzabaldevs.mobilechallengeuala.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel

@Entity(tableName = "CountriesTable")
data class CountryEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
) {
    fun toDomain() = CountryModel(
        id = id,
        country = country,
        name = name,
        latitude = latitude,
        longitude = longitude,
        isFavorite = isFavorite
    )
}
