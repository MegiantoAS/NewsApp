package com.megi.core.data.sorce.local

import com.megi.core.data.sorce.local.entity.NewsEntity
import com.megi.core.data.sorce.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsDao: NewsDao) {

    fun getDataNews(): Flow<List<NewsEntity>> = newsDao.getAllList()

    fun getFavoriteNews(): Flow<List<NewsEntity>> = newsDao.getFavoriteNews()

    suspend fun insertNews(news: List<NewsEntity>) = newsDao.insertNews(news)

    fun setFavorite(news: NewsEntity, newState: Boolean){
        news.favorite = newState
        newsDao.updateNewsFavorite(news)
    }
}