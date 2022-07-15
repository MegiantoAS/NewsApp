package com.megi.core.utils

import com.megi.core.data.sorce.local.entity.NewsEntity
import com.megi.core.data.sorce.remote.response.ArticlesItem
import com.megi.core.domain.model.NewsModel

object DataMapper {
    fun mapingResponse(input: List<ArticlesItem>): List<NewsEntity>{
        val listNews = ArrayList<NewsEntity>()
        input.map {
            val newsEntity = NewsEntity(
                title = it.title,
                name = it.source.name,
                description = it.description,
                image = it.urlToImage,
                publishAt = it.publishedAt,
                favorite = false
            )
            listNews.add(newsEntity)
        }
        return listNews
    }

    fun mapingEntity(input: List<NewsEntity>): List<NewsModel> =
        input.map {
            NewsModel(
                title = it.title,
                name = it.name,
                description = it.description,
                image = it.image,
                publishAt = it.publishAt,
                favoriteNews = it.favorite
            )
        }

    fun mapingDomain(input: NewsModel) = NewsEntity(
        title = input.title,
        name = input.name,
        description = input.description,
        image = input.image,
        publishAt = input.publishAt,
        favorite  = input.favoriteNews
    )
}