package com.raahi.youtubeapi.ui.details

import com.raahi.youtubeapi.data.network.model.VideoInfo
import com.raahi.youtubeapi.data.network.model.response.CommentsResponse

interface DetailsContract {

    interface View {

        fun initView()

        fun setUpRecyclerView()

        fun showProgressView()

        fun hideProgressView()

        fun showToast(message: String)

    }

    interface Presenter {

        fun onAttach()

        fun setVideoInfo(videoInfo: VideoInfo)

        fun getVideoInfo(): VideoInfo

        fun getComments()

        fun getCommentResponse(): CommentsResponse
    }
}