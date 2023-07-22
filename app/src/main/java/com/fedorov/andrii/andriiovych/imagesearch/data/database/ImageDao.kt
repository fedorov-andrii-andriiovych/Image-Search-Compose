package com.fedorov.andrii.andriiovych.imagesearch.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM ImageModelEntity")
    fun getAll(): Flow<List<ImageEntity>>

    @Update
    fun update(imageEntity: ImageEntity)

    @Insert
    fun insert(imageEntity: ImageEntity)

    @Delete
    fun delete(imageEntity: ImageEntity)

}