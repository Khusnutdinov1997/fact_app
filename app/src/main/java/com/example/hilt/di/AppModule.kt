package com.example.hilt.di

import com.example.hilt.date.repository.FactRepository
import com.example.hilt.date.repository.FactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFactRepository(): FactRepository{
        return FactRepositoryImpl()
    }

}