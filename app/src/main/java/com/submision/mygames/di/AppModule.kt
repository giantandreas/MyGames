package com.submision.mygames.di

import com.submision.mygames.core.domain.usecase.GameInteractor
import com.submision.mygames.core.domain.usecase.GameUseCase
import com.submision.mygames.detail.DetailViewModel
import com.submision.mygames.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }

}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}