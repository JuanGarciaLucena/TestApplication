package com.juanlucena.testapplication.di

import com.juanlucena.testapplication.BuildConfig
import com.juanlucena.testapplication.data.network.CharacterApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterApiClient(retrofit: Retrofit): CharacterApiClient {
        return retrofit.create(CharacterApiClient::class.java)
    }
}