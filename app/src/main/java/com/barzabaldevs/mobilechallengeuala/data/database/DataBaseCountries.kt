package com.barzabaldevs.mobilechallengeuala.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barzabaldevs.mobilechallengeuala.data.database.dao.CountriesDao
import com.barzabaldevs.mobilechallengeuala.data.database.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class DataBaseCountries : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}