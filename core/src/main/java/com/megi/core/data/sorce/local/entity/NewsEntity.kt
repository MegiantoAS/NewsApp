package com.megi.core.data.sorce.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "source")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "urlToImg")
    var image: String,

    @ColumnInfo(name = "publishAt")
    var publishAt: String,

    @ColumnInfo(name = "favoriteNews")
    var favorite: Boolean
)