package com.basemibrahim.photoslist.data

import com.basemibrahim.photoslist.data.remote.RemoteDataSource
import com.basemibrahim.photoslist.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource

) : BaseApiResponse() {

    suspend fun getPhotos(page: Int): Flow<NetworkResult<PhotosResponse>> {
        return flow<NetworkResult<PhotosResponse>> {
            emit(safeApiCall { remoteDataSource.getPhotos(page) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun savePhotosResponse(response: PhotosResponse) {
        localDataSource.savePhotosResponse(response)
    }

    suspend fun getResponseFromDb(): Flow<PhotosResponse> {
        return flow<PhotosResponse> {
            emit(localDataSource.getResponse())
        }.flowOn(Dispatchers.IO)
    }


}