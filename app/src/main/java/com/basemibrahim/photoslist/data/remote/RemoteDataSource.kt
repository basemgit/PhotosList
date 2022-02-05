package com.basemibrahim.photoslist.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getPhotos(page: Int) =
        retrofitService.getPhotos(page)


}