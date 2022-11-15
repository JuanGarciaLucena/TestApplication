package com.juanlucena.testapplication.data.network

import com.juanlucena.testapplication.data.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiClient {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterModel>
}