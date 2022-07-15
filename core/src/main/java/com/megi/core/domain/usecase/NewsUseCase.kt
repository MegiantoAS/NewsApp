package com.megi.core.domain.usecase

import com.megi.core.data.Resource
import com.megi.core.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
fun getListNews(): Flow<Resource<List<NewsModel>>>

fun getFavoriteNews(): Flow<List<NewsModel>>

fun setFavoriteNews(news: NewsModel, state: Boolean)
}