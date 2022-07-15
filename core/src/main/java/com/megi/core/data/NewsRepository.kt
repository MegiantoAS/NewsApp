package com.megi.core.data

import com.megi.core.data.sorce.local.LocalDataSource
import com.megi.core.data.sorce.remote.RemoteDataSource
import com.megi.core.data.sorce.remote.network.ApiResponse
import com.megi.core.data.sorce.remote.response.ArticlesItem
import com.megi.core.domain.model.NewsModel
import com.megi.core.domain.repository.InfNewsRepository
import com.megi.core.utils.AppExecutor
import com.megi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutor
): InfNewsRepository{

    override fun getListNews(): Flow<Resource<List<NewsModel>>> =
        object : com.megi.core.data.NetworkBoundResource<List<NewsModel>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<NewsModel>> {
                return localDataSource.getDataNews().map {
                    DataMapper.mapingEntity(it)
                }
            }

            override fun shouldFetch(data: List<NewsModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getListNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val listNews = DataMapper.mapingResponse(data)
                localDataSource.insertNews(listNews)
            }
        }.asFlow()

    override fun getFavoriteNews(): Flow<List<NewsModel>> {
        return localDataSource.getFavoriteNews().map { DataMapper.mapingEntity(it) }
    }

    override fun setFavoriteNews(news: NewsModel, state: Boolean) {
        val newsEntity = DataMapper.mapingDomain(news)
        appExecutors.diskIO().execute{ localDataSource.setFavorite(newsEntity, state)}
    }
}

