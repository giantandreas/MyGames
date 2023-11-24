package com.submision.mygames.core.data.source.local.room

import androidx.room.*
import com.submision.mygames.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Update
    fun updateFavoriteGame(game: GameEntity)
}