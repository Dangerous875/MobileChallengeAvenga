package com.barzabaldevs.mobilechallengeuala.di

import com.barzabaldevs.mobilechallengeuala.data.remote.CountryApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkProvider {

    @Provides
    @Singleton
    fun retrofitProvider(): Retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CountryApiClient =
        retrofit.create(CountryApiClient::class.java)
}