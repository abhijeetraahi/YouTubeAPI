package com.raahi.youtubeapi.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raahi.youtubeapi.R
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import com.raahi.youtubeapi.databinding.HomeItemBinding
import com.raahi.youtubeapi.databinding.ItemLoadingBinding
import com.raahi.youtubeapi.ui.home.HomeContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_item.view.*
import javax.inject.Inject


class HomeAdapter @Inject
constructor(private val presenter: HomeContract.Presenter<HomeContract.View>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var VIEW_TYPE_LOADING = 0
    private var VIEW_TYPE_NORMAL = 1
    private var isLoaderVisible = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> ViewHolder(
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.home_item,
                    parent,
                    false)
            )
            VIEW_TYPE_LOADING -> ProgressViewHolder(
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_loading,
                    parent,
                    false
                )
            )else -> ViewHolder(
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.home_item,
                    parent,
                    false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == presenter.getYouTubeVideoData().items.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }
    override fun getItemCount(): Int {
        return presenter.getYouTubeVideoData().items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewHolder) {
            holder.itemView.textViewTitle.text = presenter.getYouTubeVideoData().items[position].snippet?.title
            holder.itemView.textViewDes.text = presenter.getYouTubeVideoData().items[position].snippet?.description
            Picasso.get()
                .load(presenter.getYouTubeVideoData().items[position].snippet?.thumbnails?.high?.url)
                .into(holder.itemView.ImageThumb)
        }
    }


    inner class ViewHolder(binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                 presenter.onItemClicked(adapterPosition, binding.ImageThumb)
            }
        }

    }

    inner class ProgressViewHolder(binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun addLoading() {
        isLoaderVisible = true
        presenter.getYouTubeVideoData().items.add(YouTubeResponse.Item())
        notifyItemInserted(presenter.getYouTubeVideoData().items.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position: Int = presenter.getYouTubeVideoData().items.size - 1
        presenter.getYouTubeVideoData().items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addAllItems(){
        presenter.addAllItems()
    }
}