package com.basemibrahim.photoslist.data.remote

import com.basemibrahim.photoslist.data.PhotosResponse
import com.basemibrahim.photoslist.utils.Constants.Companion.PHOTOS_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET(PHOTOS_URL)
    suspend fun getPhotos(
        @Query("page") page: Int
    ): Response<PhotosResponse>


}