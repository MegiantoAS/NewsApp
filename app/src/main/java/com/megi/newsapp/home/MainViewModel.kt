package com.megi.newsapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.megi.core.domain.usecase.NewsUseCase

class MainViewModel(newsUseCase: NewsUseCase): ViewModel() {
    val news = newsUseCase.getListNews().asLiveData()
}