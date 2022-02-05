package com.basemibrahim.photoslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basemibrahim.photoslist.data.Photo
import com.basemibrahim.photoslist.databinding.AdBannerViewItemBinding
import com.basemibrahim.photoslist.databinding.GridViewItemBinding
import com.basemibrahim.photoslist.utils.Constants.Companion.AD_TYPE
import com.basemibrahim.photoslist.utils.Constants.Companion.LIST_AD_DELTA
import com.basemibrahim.photoslist.utils.Constants.Companion.NORMAL_TYPE


class PhotosAdapter(data: List<Photo>) : RecyclerView.Adapter<
        RecyclerView.ViewHolder>() {
    var list: List<Photo> = data


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            NORMAL_TYPE -> return PhotosViewHolder(
                GridViewItemBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
            )

            AD_TYPE -> return AdBannerViewHolder(
                AdBannerViewItemBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
            )
        }
        return PhotosViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == NORMAL_TYPE && holder is PhotosViewHolder)
        {
           holder.bind(list[getRealPosition(position)])
        }

    }

    override fun getItemCount(): Int {
        var additionalContent = 0
        if (list.size> 0 && LIST_AD_DELTA > 0 && list.size > LIST_AD_DELTA) {
            additionalContent = (list.size + (list.size)/ LIST_AD_DELTA) / LIST_AD_DELTA;
        }
        return list.size + additionalContent
    }

    override fun getItemViewType(position: Int): Int {
if (position > 0 &&position % LIST_AD_DELTA == 0)
    return AD_TYPE
    return NORMAL_TYPE
    }

   private fun getRealPosition(position: Int): Int
    {
        if(LIST_AD_DELTA == 0)
            return position
        else return position - position / LIST_AD_DELTA
    }

    inner class PhotosViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo

//            binding.root.setOnClickListener {
//                val action = ListFragmentDirections.actionListFragmentToDetailsFragment(
//                    title = movie.title,
//                    img = movie.poster_path,
//                    description = movie.overview,
//                    releaseDate = movie.release_date,
//                    votesAverage = movie.vote_average.toString(),
//                    popularity = movie.popularity.toString(),
//                    movieId = movie.id,
//                    isFavourite = movie.isFavourite
//                )
//                binding.root.findNavController().navigate(action)
//            }
            binding.executePendingBindings()
        }
    }



    inner class AdBannerViewHolder(
        private var binding:
        AdBannerViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.executePendingBindings()
        }

    }



}