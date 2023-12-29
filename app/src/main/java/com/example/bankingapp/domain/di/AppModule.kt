package com.example.bankingapp.domain.di

import com.example.bankingapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHomeRepository(): HomeRepository {
        return HomeRepository()
    }
}