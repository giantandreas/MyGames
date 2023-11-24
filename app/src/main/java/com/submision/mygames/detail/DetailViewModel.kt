package com.submision.mygames.detail

import androidx.lifecycle.ViewModel
import com.submision.mygames.core.domain.model.Game
import com.submision.mygames.core.domain.usecase.GameUseCase

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: Game, newStatus: Boolean){
        gameUseCase.setFavoriteGames(game, newStatus)
    }
}