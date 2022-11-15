package com.juanlucena.testapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.juanlucena.testapplication.data.database.dao.CharacterDao
import com.juanlucena.testapplication.data.database.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class RickMortyDatabase: RoomDatabase() {

    abstract fun getCharacterDao() : CharacterDao

}