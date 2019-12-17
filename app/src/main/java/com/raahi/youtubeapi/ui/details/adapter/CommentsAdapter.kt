package com.raahi.youtubeapi.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raahi.youtubeapi.R
import com.raahi.youtubeapi.databinding.CommentItemBinding
import com.raahi.youtubeapi.ui.details.DetailsContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentsAdapter constructor(private val presenter: DetailsContract.Presenter) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.comment_item,
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return presenter.getCommentResponse().items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvName.text = presenter.getCommentResponse().items[position].snippet?.topLevelComment?.snippet?.authorDisplayName
        holder.itemView.tvFeedback.text = presenter.getCommentResponse().items[position].snippet?.topLevelComment?.snippet?.textDisplay
        Picasso.get()
            .load(presenter.getCommentResponse().items[position].snippet?.topLevelComment?.snippet?.authorProfileImageUrl)
            .into(holder.itemView.profilePhoto)
    }


    inner class ViewHolder(binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}