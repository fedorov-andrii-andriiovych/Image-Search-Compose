package com.fedorov.andrii.andriiovych.imagesearch.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageModelEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "url") val url: String,
)