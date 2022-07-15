package com.megi.newsapp.detail

import androidx.lifecycle.ViewModel
import com.megi.core.domain.model.NewsModel
import com.megi.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    fun setFavNews(news: NewsModel, newState: Boolean){
        newsUseCase.setFavoriteNews(news, newState)
    }
}