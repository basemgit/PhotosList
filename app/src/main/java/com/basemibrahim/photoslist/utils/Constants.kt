package com.basemibrahim.photoslist.utils
//const val BASE_URL = "https://www.flickr.com/services/rest/
// ?method=flickr.photos.search&
// format=json&nojsoncallback=50
// &text=Color&page=1&per_page=20&api_key=d17378e37e555ebef55ab86c4180e8dc"

class Constants {
    companion object {
        const val API_KEY = "d17378e37e555ebef55ab86c4180e8dc"
        const val BASE_URL = "https://www.flickr.com/"
        const val PHOTOS_URL = "services/rest/?method=flickr.photos.search&format=json&nojsoncallback=50&text=Color&per_page=20"
        const val NETWORK_TAG = "NETWORK"
        const val NORMAL_TYPE = 0
        const val AD_TYPE = 1
         const val LIST_AD_DELTA = 6


    }
}