package com.barzabaldevs.mobilechallengeuala.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barzabaldevs.mobilechallengeuala.data.database.entities.CountryEntity

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countriestable")
    suspend fun getAllCountries(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<CountryEntity>)
}