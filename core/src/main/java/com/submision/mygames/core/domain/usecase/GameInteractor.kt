package com.submision.mygames.core.domain.usecase

import com.submision.mygames.core.data.Resource
import com.submision.mygames.core.domain.model.Game
import com.submision.mygames.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGames(): Flow<Resource<List<Game>>> = gameRepository.getAllGames()
    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGames()
    override fun setFavoriteGames(game: Game, newState: Boolean) = gameRepository.setFavoriteGames(game, newState)
}