package com.submision.mygames.core.data.source.remote

import android.util.Log
import com.submision.mygames.core.data.source.remote.network.ApiResponse
import com.submision.mygames.core.data.source.remote.network.ApiService
import com.submision.mygames.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListGames(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                if (response.isNotEmpty()){
                    val n = minOf(response.size, 15)
                    val gameList = response.subList(0, n)
                    emit(ApiResponse.Success(gameList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}