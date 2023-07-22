package com.fedorov.andrii.andriiovych.imagesearch.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM ImageModelEntity")
    fun getAll(): Flow<List<ImageModelEntity>>

    @Update
    fun update(imageModelEntity: ImageModelEntity)

    @Insert
    fun insert(imageModelEntity: ImageModelEntity)

    @Delete
    fun delete(imageModelEntity: ImageModelEntity)

}