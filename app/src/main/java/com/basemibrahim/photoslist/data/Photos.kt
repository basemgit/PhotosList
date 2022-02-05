package com.basemibrahim.photoslist.data

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    var photo: List<Photo>,
    val total: Int
)