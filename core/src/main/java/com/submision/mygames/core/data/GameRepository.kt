package com.submision.mygames.core.data

import com.submision.mygames.core.data.source.local.LocalDataSource
import com.submision.mygames.core.data.source.remote.RemoteDataSource
import com.submision.mygames.core.data.source.remote.network.ApiResponse
import com.submision.mygames.core.data.source.remote.response.GameResponse
import com.submision.mygames.core.domain.model.Game
import com.submision.mygames.core.domain.repository.IGameRepository
import com.submision.mygames.core.utils.AppExecutors
import com.submision.mygames.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getAllGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> {
                return remoteDataSource.getListGames()
            }

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGames(game: Game, newState: Boolean) {
        val gameEntities = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteGame(gameEntities, newState)
        }
    }


}