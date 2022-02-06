package com.basemibrahim.photoslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basemibrahim.photoslist.data.Photos
import com.basemibrahim.photoslist.data.PhotosResponse
import com.basemibrahim.photoslist.data.Repository
import com.basemibrahim.photoslist.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<PhotosResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<PhotosResponse>> = _response

    private val _responseDB: MutableLiveData<PhotosResponse> = MutableLiveData()
    val responseDB: LiveData<PhotosResponse>  = _responseDB

    fun getPhotos(page: Int) = viewModelScope.launch {
        repository.getPhotos(page).collect { values ->
            _response.value = values
        }
    }

    fun savePhotosResponseToDb(response: PhotosResponse) = viewModelScope.launch {
        repository.savePhotosResponse(response)
    }


    fun fetchResponseFromDb() = viewModelScope.launch {
        repository.getResponseFromDb().collect { values ->
            _responseDB.value = values
        }
    }

}