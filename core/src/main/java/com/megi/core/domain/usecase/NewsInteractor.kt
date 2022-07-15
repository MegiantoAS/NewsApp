package com.megi.core.domain.usecase

import com.megi.core.data.NewsRepository
import com.megi.core.domain.model.NewsModel

class NewsInteractor(private val newsRepository: NewsRepository): NewsUseCase{
    override fun getListNews() = newsRepository.getListNews()
    override fun getFavoriteNews() = newsRepository.getFavoriteNews()
    override fun setFavoriteNews(news: NewsModel, state: Boolean) = newsRepository.setFavoriteNews(news, state)
}