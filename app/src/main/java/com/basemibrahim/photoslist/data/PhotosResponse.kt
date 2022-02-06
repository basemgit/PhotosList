package com.basemibrahim.photoslist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotosResponse(
    @PrimaryKey
    val photos: Photos,
    val stat: String
)