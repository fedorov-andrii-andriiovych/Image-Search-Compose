package com.fedorov.andrii.andriiovych.imagesearch.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM ImageEntity")
    fun getAll(): Flow<List<ImageEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(imageEntity: ImageEntity)

    @Query("SELECT COUNT(*) FROM ImageEntity WHERE url = :url")
    suspend fun getImageCountByUrl(url: String): Int

    @Transaction
    suspend fun insertWithCheck(imageEntity: ImageEntity) {
        val count = getImageCountByUrl(imageEntity.url)
        if (count == 0) {
            insert(imageEntity)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageEntity: ImageEntity)

    @Delete
    suspend fun delete(imageEntity: ImageEntity)

}