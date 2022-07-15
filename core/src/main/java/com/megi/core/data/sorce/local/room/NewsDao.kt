package com.megi.core.data.sorce.local.room

import androidx.room.*
import com.megi.core.data.sorce.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllList(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE favoriteNews = 1")
    fun getFavoriteNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    fun updateNewsFavorite(news: NewsEntity)
}