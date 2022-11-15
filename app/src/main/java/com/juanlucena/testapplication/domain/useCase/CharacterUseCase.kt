package com.juanlucena.testapplication.domain.useCase

import android.util.Log
import com.juanlucena.testapplication.data.database.entity.CharacterEntity
import com.juanlucena.testapplication.data.database.entity.toDatabase
import com.juanlucena.testapplication.data.model.CharacterInfo
import com.juanlucena.testapplication.data.model.CharacterModel
import com.juanlucena.testapplication.data.repository.CharacterRepository
import com.juanlucena.testapplication.domain.model.CharacterDomain
import com.juanlucena.testapplication.domain.model.toDomain
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend fun getCharacters(): List<CharacterDomain>{

        val characters = repository.getCharactersFromDatabase()
        val characterDomainList = mutableListOf<CharacterDomain>()
        val characterEntityList = mutableListOf<CharacterEntity>()

        return if(characters.isEmpty()){
            //Log.i("CHARACTER", "FROM API")
            val characterFromApi = repository.getCharactersFromApi()
            characterFromApi.forEach { item -> characterEntityList.add(item.toDatabase()) }
            repository.insertCharacters(characterEntityList)
            characterFromApi
        }else{
            //Log.i("CHARACTER", "FROM DB")
            characters.forEach { item -> characterDomainList.add(item.toDomain())}
            characterDomainList
        }
    }

    suspend fun isFavourite(isFavourite: Boolean, id: Int){
        repository.isFavourite(isFavourite, id)
    }
}