package com.basemibrahim.photoslist.di

import android.content.Context
import androidx.room.Room
import com.basemibrahim.photoslist.data.local.db.AppDatabase
import com.basemibrahim.photoslist.data.local.db.PhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun providePhotosDao(database: AppDatabase): PhotosDao {
        return database.photosDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "PhotosList.db"
        ).build()
    }
}