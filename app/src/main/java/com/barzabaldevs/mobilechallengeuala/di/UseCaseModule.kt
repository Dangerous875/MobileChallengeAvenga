package com.barzabaldevs.mobilechallengeuala.di

import com.barzabaldevs.mobilechallengeuala.data.RepositoryImpl
import com.barzabaldevs.mobilechallengeuala.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository
}