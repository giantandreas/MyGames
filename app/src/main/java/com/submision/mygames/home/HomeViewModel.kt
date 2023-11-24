package com.submision.mygames.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submision.mygames.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val games = gameUseCase.getAllGames().asLiveData()
}