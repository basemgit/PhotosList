
package com.basemibrahim.photoslist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.basemibrahim.photoslist.data.PhotosResponse
import com.basemibrahim.photoslist.utils.Converters

@Database(entities = arrayOf(PhotosResponse::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun photosDao(): PhotosDao
}
