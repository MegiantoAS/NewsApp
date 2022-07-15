package com.megi.newsapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.megi.core.domain.usecase.NewsUseCase

class FavoriteViewModel(newsUseCase: NewsUseCase): ViewModel() {
    val favoriteNews = newsUseCase.getFavoriteNews().asLiveData()
}