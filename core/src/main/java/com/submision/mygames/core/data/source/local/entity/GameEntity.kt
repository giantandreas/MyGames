package com.submision.mygames.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity (
    @PrimaryKey
    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "platform")
    var platform: String,

    @ColumnInfo(name = "publisher")
    var publisher: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String

)