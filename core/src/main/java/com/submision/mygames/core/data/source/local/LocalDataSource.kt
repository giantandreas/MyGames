package com.submision.mygames.core.data.source.local

import com.submision.mygames.core.data.source.local.entity.GameEntity
import com.submision.mygames.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {
    fun getAllGames(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGame(games: List<GameEntity>) = gameDao.insertGames(games)

    fun setFavoriteGame(game: GameEntity, newState: Boolean){
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}