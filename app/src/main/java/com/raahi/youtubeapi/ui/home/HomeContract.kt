package com.raahi.youtubeapi.ui.home

import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse1
import com.raahi.youtubeapi.ui.base.BaseContract

interface HomeContract {

    interface View: BaseContract.View{
        fun setUpRecyclerView()

        fun showProgressView()

        fun hideProgressView()
    }


    interface Presenter<V: View>: BaseContract.Presenter<V>{

        fun getYouTubeVideos()

        fun getYouTubeVideoData(): YouTubeResponse1
    }
}