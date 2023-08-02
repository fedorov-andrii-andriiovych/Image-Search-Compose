package com.fedorov.andrii.andriiovych.imagesearch.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fedorov.andrii.andriiovych.imagesearch.data.database.models.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}