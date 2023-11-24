package com.submision.mygames.core.utils

import com.submision.mygames.core.data.source.local.entity.GameEntity
import com.submision.mygames.core.data.source.remote.response.GameResponse
import com.submision.mygames.core.domain.model.Game

object DataMapper {
    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                name = it.name,
                description = it.description,
                genre = it.genre,
                platform = it.platform,
                publisher = it.publisher,
                isFavorite = it.isFavorite,
                imageUrl = it.imageUrl
            )
        }

    fun mapResponseToEntities(input: List<GameResponse>): List<GameEntity> =
        input.map {
            GameEntity(
                gameId = it.id,
                name = it.title,
                description = it.shortDescription,
                genre = it.genre,
                platform = it.platform,
                publisher = it.publisher,
                isFavorite = false,
                imageUrl = it.thumbnail
            )
        }

    fun mapDomainToEntity(input: Game): GameEntity {
        return GameEntity(
            gameId = input.gameId,
            name = input.name,
            description = input.description,
            genre = input.genre,
            platform = input.platform,
            publisher = input.publisher,
            isFavorite = input.isFavorite,
            imageUrl = input.imageUrl
        )
    }

}