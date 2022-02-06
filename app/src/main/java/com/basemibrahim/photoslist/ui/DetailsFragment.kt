package com.basemibrahim.photoslist.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.basemibrahim.photoslist.R
import com.basemibrahim.photoslist.databinding.DetailsFragmentBinding
import com.basemibrahim.photoslist.utils.Constants.Companion.IMG
import com.basemibrahim.photoslist.viewmodel.MainViewModel


class DetailsFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var _binding: DetailsFragmentBinding
    private lateinit var img: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            img = it.getParcelable<Bitmap>(IMG)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        img?.let {
            _binding.image.load(it)
            {
                placeholder(R.drawable.ic_baseline_refresh_24)
                error(R.drawable.ic_outline_broken_image_24)
            }
        }

    }

}