package com.juanlucena.testapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanlucena.testapplication.domain.model.CharacterDomain
import com.juanlucena.testapplication.domain.useCase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterUseCase: CharacterUseCase): ViewModel() {

    val characterListResult = MutableLiveData<List<CharacterDomain>>()
    val isFavouriteLiveData = MutableLiveData<Boolean>()

    fun getCharacters(){
        viewModelScope.launch {
            characterListResult.postValue(characterUseCase.getCharacters())
        }
    }

    fun isFavourite(isFavourite: Boolean, id: Int){
        viewModelScope.launch {
            characterUseCase.isFavourite(isFavourite, id)
            isFavouriteLiveData.postValue(isFavourite)
        }
    }
}