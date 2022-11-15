package com.juanlucena.testapplication.data.network

import com.juanlucena.testapplication.data.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val apiClient: CharacterApiClient) {

    suspend fun getCharacters(): CharacterModel{
        return withContext(Dispatchers.IO){
            val response = apiClient.getCharacters()
            response.body() ?: CharacterModel(emptyList())
        }
    }
}