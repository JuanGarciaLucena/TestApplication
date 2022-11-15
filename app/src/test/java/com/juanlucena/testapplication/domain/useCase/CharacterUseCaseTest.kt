package com.juanlucena.testapplication.domain.useCase

import com.juanlucena.testapplication.data.database.entity.CharacterEntity
import com.juanlucena.testapplication.data.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class CharacterUseCaseTest{

    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var characterUseCase: CharacterUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        characterUseCase = CharacterUseCase(characterRepository)
    }

    @Test
    fun `If database is empty get characters from api`() = runBlocking {

        //Given
        coEvery { characterRepository.getCharactersFromDatabase() } returns emptyList()

        //When
        characterUseCase.getCharacters()

        //When
        coVerify (exactly = 1) { characterRepository.getCharactersFromApi() }
    }

    @Test
    fun `When database is not empty return character`() = runBlocking {

        val characterList = listOf(CharacterEntity(
            id = 0,
            name = "SuperPaco",
            status = "status",
            species = "human",
            type = "type",
            gender = "male",
            originName = "originName",
            originUrl = "www.origin.com",
            locationName = "location",
            locationUrl = "www.location.com",
            image = "image",
            episode = listOf("episode"),
            url = "www.url.com",
            created = "today",
            isFavourite = false
        ))

        coEvery { characterRepository.getCharactersFromDatabase() } returns characterList

        characterUseCase.getCharacters()

        coVerify (exactly = 1) { characterRepository.getCharactersFromDatabase() }
    }
}