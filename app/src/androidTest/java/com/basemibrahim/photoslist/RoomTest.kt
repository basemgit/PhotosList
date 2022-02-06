package com.basemibrahim.photoslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.basemibrahim.photoslist.data.Photo
import com.basemibrahim.photoslist.data.Photos
import com.basemibrahim.photoslist.data.PhotosResponse
import com.basemibrahim.photoslist.data.local.db.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RoomTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: AppDatabase

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertResponse() = runBlockingTest {
        // GIVEN - Insert data.
        val response = PhotosResponse(
            Photos(
                1, 10, 5, listOf(
                    Photo(
                        1, "id", 1, 1, 1, "owner", "secret",
                        "server", "title"
                    ),
                    Photo(
                        1, "id", 1, 1, 1, "owner", "secret",
                        "server", "title"
                    )
                )
            ,10), "stat"
        )
        database.photosDao().insertResponse(response)

        // WHEN - Get the data
        val loaded = database.photosDao().getResponse()

        // THEN - The loaded data contains the expected values.
        assertThat<PhotosResponse>(loaded as PhotosResponse, notNullValue())
        assertThat(loaded.photos, `is`(response.photos))
        assertThat(loaded.photos.page, `is`(response.photos.page))

    }

}