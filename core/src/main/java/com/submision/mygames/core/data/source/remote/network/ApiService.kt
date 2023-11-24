package com.submision.mygames.core.data.source.remote.network

import com.submision.mygames.core.data.source.remote.response.GameResponse
import retrofit2.http.GET

interface ApiService {
    @GET("games")
    suspend fun getList(): List<GameResponse>
}