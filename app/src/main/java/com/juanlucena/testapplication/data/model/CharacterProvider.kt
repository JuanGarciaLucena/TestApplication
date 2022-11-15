package com.juanlucena.testapplication.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterProvider @Inject constructor() {
    var characterList: List<CharacterInfo> = emptyList()
}