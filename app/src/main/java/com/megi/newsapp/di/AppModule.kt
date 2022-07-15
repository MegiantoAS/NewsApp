package com.megi.newsapp.di

import com.megi.core.domain.usecase.NewsInteractor
import com.megi.core.domain.usecase.NewsUseCase
import com.megi.newsapp.detail.DetailViewModel
import com.megi.newsapp.home.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory <NewsUseCase>{
        NewsInteractor(get())
    }
}

val viewModelModule = module {
    viewModel  {
        MainViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }
}