package com.juanlucena.testapplication.di

import android.content.Context
import androidx.room.Room
import com.juanlucena.testapplication.data.database.RickMortyDatabase
import com.juanlucena.testapplication.utils.DataBaseUtils.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, RickMortyDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCharacterDao(db: RickMortyDatabase) = db.getCharacterDao()
}