package com.juanlucena.testapplication.domain.model

import com.juanlucena.testapplication.data.database.entity.CharacterEntity
import com.juanlucena.testapplication.data.model.CharacterInfo

data class CharacterDomain (

    val id: Int,
    val name: String,
    val status : String,
    val species : String,
    val type : String,
    val gender : String,
    val originName : String,
    val originUrl : String,
    val locationName : String,
    val locationUrl : String,
    val image : String,
    val episode : List<String>,
    val url : String,
    val created : String,
    var isFavourite : Boolean
) : java.io.Serializable

fun CharacterEntity.toDomain() : CharacterDomain {

    return CharacterDomain(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        originName = originName,
        originUrl = originUrl,
        locationName = locationName,
        locationUrl = locationUrl,
        image = image,
        episode = episode,
        url = url,
        created = created,
        isFavourite = isFavourite
    )
}

fun CharacterInfo.toDomain() : CharacterDomain{

        return CharacterDomain(
            id = id,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
            originName = origin.name,
            originUrl = origin.url,
            locationName = location.name,
            locationUrl = location.url,
            image = image,
            episode = episode,
            url = url,
            created = created,
            isFavourite = false
        )
}