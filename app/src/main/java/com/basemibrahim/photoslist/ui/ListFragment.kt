package com.basemibrahim.photoslist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basemibrahim.photoslist.R
import com.basemibrahim.photoslist.data.Photo
import com.basemibrahim.photoslist.data.PhotosResponse
import com.basemibrahim.photoslist.databinding.ListFragmentBinding
import com.basemibrahim.photoslist.utils.Constants.Companion.NETWORK_TAG
import com.basemibrahim.photoslist.utils.NetworkResult
import com.basemibrahim.photoslist.utils.Utils
import com.basemibrahim.photoslist.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var _binding: ListFragmentBinding
    private var loading = true
    private var totalPages = 0
    var page = 1
    var list = ArrayList<Photo>()
    private lateinit var adapter: PhotosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater)
        _binding.lifecycleOwner = this
        adapter = PhotosAdapter(list)
        _binding.list.adapter = adapter
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchResponse(page)
        loadMore()
    }

    fun fetchResponse(page: Int) {
        if (Utils.isNetworkAvailable(requireContext())) {
            mainViewModel.getPhotos(page)
            _binding.pbDog.visibility = View.VISIBLE
            processData()
        } else {
            Snackbar.make(
                _binding.root,
                resources.getString(R.string.no_internet),
                Snackbar.LENGTH_SHORT
            )
                .show()
            _binding.pbDog.visibility = View.GONE
            mainViewModel.fetchResponseFromDb()
            processLocalData()

        }
    }

    private fun processData() {
        mainViewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        totalPages = it.photos.pages
                        loading = false;
                        showPhotos(it)
                    }
                    _binding.pbDog.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    _binding.pbDog.visibility = View.GONE
                    Log.d(NETWORK_TAG, response.message.toString())
                }

                is NetworkResult.Loading -> {
                    _binding.pbDog.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun showPhotos(data: PhotosResponse) {
        for (item in data.photos.photo) {
            if (!list.contains(item)) {
                list.add(item)
            }
        }
        data.photos.photo = ArrayList()
        (data.photos.photo as ArrayList<Photo>).addAll(list)
        mainViewModel.savePhotosResponseToDb(data)
        adapter.notifyDataSetChanged()
    }

    private fun processLocalData() {
        mainViewModel.responseDB.observe(viewLifecycleOwner) { response ->
            response?.let {
                showMoviesFromDb(it)
            }
        }
    }

    private fun showMoviesFromDb(localData: PhotosResponse) {
        for (item in localData.photos.photo) {
            if (!list.contains(item)) {
                list.add(item)
            }
        }
        adapter.notifyDataSetChanged()
    }
    fun loadMore() {

        if (Utils.isNetworkAvailable(requireContext())) {
            _binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (recyclerView != null) {
                        super.onScrolled(recyclerView, dx, dy)
                    }


                    val visibleItemCount =
                        (_binding.list.layoutManager as LinearLayoutManager).childCount
                    val totalItemCount =
                        (_binding.list.layoutManager as LinearLayoutManager).itemCount
                    val firstVisible =
                        (_binding.list.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (!loading && (visibleItemCount + firstVisible) >= totalItemCount && page < totalPages && dy > 0) {
                        loading = true
                        page += 1
                        fetchResponse(page)
                    }
                }
            })

        }
    }

}

