package com.juanlucena.testapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juanlucena.testapplication.data.database.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table")
    suspend fun getAllCharacters(): MutableList<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("UPDATE character_table SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun isFavourite(isFavourite: Boolean, id: Int)
}