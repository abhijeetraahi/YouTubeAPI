package com.raahi.youtubeapi.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raahi.youtubeapi.R
import com.raahi.youtubeapi.databinding.HomeItemBinding
import com.raahi.youtubeapi.ui.home.HomeContract
import com.squareup.picasso.Picasso
import javax.inject.Inject

class HomeAdapter @Inject
constructor(private val presenter: HomeContract.Presenter<HomeContract.View>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.home_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return presenter.getYouTubeVideoData().items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding.model = mPresenter.getPostsResponse()[position]

        holder.binding.textViewTitle.text = presenter.getYouTubeVideoData().items[position].snippet?.title
        holder.binding.textViewDes.text = presenter.getYouTubeVideoData().items[position].snippet?.description
        holder.binding.textViewDate.text = presenter.getYouTubeVideoData().items[position].snippet?.publishedAt
        Picasso.get().load(presenter.getYouTubeVideoData().items[position].snippet?.thumbnails?.default?.url).into(holder.binding.ImageThumb)

    }

    inner class ViewHolder(var binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
               // mPresenter.itemClicked(adapterPosition)
            }
        }
    }
}