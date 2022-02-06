package com.basemibrahim.photoslist.utils

import androidx.room.TypeConverter
import com.basemibrahim.photoslist.data.Photo
import com.basemibrahim.photoslist.data.Photos
import com.basemibrahim.photoslist.data.PhotosResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromPhotosResponse(value: PhotosResponse): String {
        val gson = Gson()
        val type = object : TypeToken<PhotosResponse>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPhotosResponse(value: String): PhotosResponse {
        val gson = Gson()
        val type = object : TypeToken<PhotosResponse>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPhotos(value: Photos): String {
        val gson = Gson()
        val type = object : TypeToken<Photos>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPhotos(value: String): Photos {
        val gson = Gson()
        val type = object : TypeToken<com.basemibrahim.photoslist.data.Photos>() {}.type
        return gson.fromJson(value, type)
    }


    @TypeConverter
    fun fromPhotoList(value: ArrayList<Photo>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Photo>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPhotoList(value: String): ArrayList<Photo> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Photo>>() {}.type
        return gson.fromJson(value, type)
    }


}