package com.basemibrahim.photoslist.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.basemibrahim.photoslist.R


@BindingAdapter("farm", "server", "id", "secret")
fun bindImage(imgView: ImageView, farm: Int,server: String,id: String,secret: String) {
   // http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg

        imgView.load("http://farm$farm.static.flickr.com/$server/${id}_${secret}.jpg")
        {
            placeholder(R.drawable.ic_baseline_refresh_24)
            error(R.drawable.ic_outline_broken_image_24)
            listener(
                onError = {request, throwable ->
                    Log.d(Constants.NETWORK_TAG,throwable.message.toString())
                }
            )
        }
}

