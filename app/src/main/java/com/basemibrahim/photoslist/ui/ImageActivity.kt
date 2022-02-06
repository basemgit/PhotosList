package com.basemibrahim.photoslist.ui

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.load
import com.basemibrahim.photoslist.R
import com.basemibrahim.photoslist.databinding.ActivityImageBinding
import com.basemibrahim.photoslist.utils.Constants.Companion.IMG


class ImageActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityImageBinding
    private lateinit var img: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageBinding.inflate(layoutInflater)
        hideSystemBars()

        setContentView(_binding.root)
        img = intent.getParcelableExtra<Bitmap>(IMG)!!

        img?.let {
            _binding.image.load(it)
            {
                placeholder(R.drawable.ic_baseline_refresh_24)
                error(R.drawable.ic_outline_broken_image_24)
            }
        }
    }

        private fun hideSystemBars() {
            val windowInsetsController =
                window?.let { ViewCompat.getWindowInsetsController(it?.decorView) } ?: return
            // Configure the behavior of the hidden system bars
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            // Hide both the status bar and the navigation bar
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }

    }
