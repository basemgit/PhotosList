package com.basemibrahim.photoslist.data.local.db;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basemibrahim.photoslist.data.PhotosResponse


@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertResponse(item: PhotosResponse)

    @Query("select * from photosresponse")
    fun getResponse() : PhotosResponse

}
