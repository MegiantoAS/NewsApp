package com.megi.core.data.sorce.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.megi.core.data.sorce.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}