

package com.basemibrahim.photoslist.data

import com.basemibrahim.photoslist.data.local.db.PhotosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val photosDao: PhotosDao) {

    suspend  fun savePhotosResponse (response: PhotosResponse) {
       withContext(Dispatchers.IO)
       {
           photosDao.insertResponse(response)
       }
    }

    suspend fun getResponse() : PhotosResponse {
       var response : PhotosResponse
       withContext(Dispatchers.IO)
       {
           response = photosDao.getResponse()
       }
       return response
    }


}
