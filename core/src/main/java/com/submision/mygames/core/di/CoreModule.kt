package com.submision.mygames.core.di

import androidx.room.Room
import com.submision.mygames.core.data.GameRepository
import com.submision.mygames.core.data.source.local.LocalDataSource
import com.submision.mygames.core.data.source.local.room.GameDatabase
import com.submision.mygames.core.data.source.remote.RemoteDataSource
import com.submision.mygames.core.data.source.remote.network.ApiService
import com.submision.mygames.core.domain.repository.IGameRepository
import com.submision.mygames.core.utils.AppExecutors
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val hostName = "www.mmobomb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/I8UPily9NpJ+lHDZCUOjXN0nCsrfohuHhDp9Wa6VSjU=")
            .add(hostName, "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.mmobomb.com/api1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository> {
        GameRepository(get(), get(), get())
    }
}