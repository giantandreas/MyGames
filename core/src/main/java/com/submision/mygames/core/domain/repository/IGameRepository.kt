package com.submision.mygames.core.domain.repository

import com.submision.mygames.core.data.Resource
import com.submision.mygames.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGames(game: Game, newState: Boolean)
}