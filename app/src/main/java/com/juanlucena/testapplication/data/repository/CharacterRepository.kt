package com.juanlucena.testapplication.data.repository

import com.juanlucena.testapplication.data.database.dao.CharacterDao
import com.juanlucena.testapplication.data.database.entity.CharacterEntity
import com.juanlucena.testapplication.data.database.entity.toDatabase
import com.juanlucena.testapplication.data.model.CharacterInfo
import com.juanlucena.testapplication.data.model.CharacterModel
import com.juanlucena.testapplication.data.model.CharacterProvider
import com.juanlucena.testapplication.data.network.CharacterApiClient
import com.juanlucena.testapplication.data.network.CharacterService
import com.juanlucena.testapplication.domain.model.CharacterDomain
import com.juanlucena.testapplication.domain.model.toDomain
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiClient: CharacterService,
    private val characterDao: CharacterDao
) {

    suspend fun getCharactersFromApi(): List<CharacterDomain>{
        val response = apiClient.getCharacters()
        val characterDomainList = mutableListOf<CharacterDomain>()

        response.characterInfoList.forEach { item ->
            characterDomainList.add(item.toDomain())
        }

        return characterDomainList
    }

    suspend fun getCharactersFromDatabase(): List<CharacterEntity>{
        return characterDao.getAllCharacters()
    }

    suspend fun insertCharacters(characterList: List<CharacterEntity>){
        characterDao.insertAll(characterList)
    }

    suspend fun isFavourite(isFavourite: Boolean, id: Int){
        characterDao.isFavourite(isFavourite, id)
    }
}