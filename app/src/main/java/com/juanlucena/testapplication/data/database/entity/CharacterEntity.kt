package com.juanlucena.testapplication.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanlucena.testapplication.data.model.CharacterInfo
import com.juanlucena.testapplication.domain.model.CharacterDomain

@Entity(tableName = "character_table")
data class CharacterEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "status") val status : String = "",
    @ColumnInfo(name = "species") val species : String = "",
    @ColumnInfo(name = "type") val type : String = "",
    @ColumnInfo(name = "gender") val gender : String = "",
    @ColumnInfo(name = "originName") val originName : String = "",
    @ColumnInfo(name = "originUrl") val originUrl : String = "",
    @ColumnInfo(name = "locationName") val locationName : String = "",
    @ColumnInfo(name = "locationUrl") val locationUrl : String = "",
    @ColumnInfo(name = "image") val image : String = "",
    @ColumnInfo(name = "episode") val episode : List<String>,
    @ColumnInfo(name = "url") val url : String = "",
    @ColumnInfo(name = "created") val created : String,
    @ColumnInfo(name = "isFavourite") val isFavourite : Boolean

)

fun CharacterInfo.toDatabase() : CharacterEntity{

        return CharacterEntity(
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

fun CharacterDomain.toDatabase() : CharacterEntity{

    return CharacterEntity(
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