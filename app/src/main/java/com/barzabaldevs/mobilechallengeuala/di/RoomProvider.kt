package com.barzabaldevs.mobilechallengeuala.di

import android.content.Context
import androidx.room.Room
import com.barzabaldevs.mobilechallengeuala.data.database.DataBaseCountries
import com.barzabaldevs.mobilechallengeuala.data.database.dao.CountriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomProvider {

    private const val DATABASE_NAME = "Countries-db"

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): DataBaseCountries =
        Room.databaseBuilder(context, DataBaseCountries::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideCharacterDao(roomDatabase: DataBaseCountries): CountriesDao =
        roomDatabase.countriesDao()
}