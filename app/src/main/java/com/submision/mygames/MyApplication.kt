package com.submision.mygames

import android.app.Application
import com.submision.mygames.core.di.databaseModule
import com.submision.mygames.core.di.networkModule
import com.submision.mygames.core.di.repositoryModule
import com.submision.mygames.di.useCaseModule
import com.submision.mygames.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}