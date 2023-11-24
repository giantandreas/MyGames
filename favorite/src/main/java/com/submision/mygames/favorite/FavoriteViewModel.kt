package com.submision.mygames.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submision.mygames.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase): ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()
}