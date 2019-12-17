package com.raahi.youtubeapi.ui.home

import com.raahi.youtubeapi.data.network.model.VideoInfo
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import com.raahi.youtubeapi.ui.base.BaseContract

interface HomeContract {

    interface View: BaseContract.View{

        fun initToolBar()

        fun initView()

        fun setUpRecyclerView()

        fun showProgressView()

        fun hideProgressView()

        fun notifyRecyclerView()

        fun launchDetailsActivity(videoInfo: VideoInfo, view: android.view.View)

        fun refreshData(view: android.view.View)

        fun showRefreshDataView()

        fun scheduleJob()
    }


    interface Presenter<V: View>: BaseContract.Presenter<V>{

        fun getCurrentPage(): Int

        fun getTotalPage(): Int

        fun getItemCount(): Int

        fun isLastPage(): Boolean

        fun isLoading(): Boolean

        fun setCurrentPage(currentPage: Int)

        fun increaseCurrentPage()

        fun setTotalPage(totalPage: Int)

        fun setItemCount(itemCount: Int)

        fun isLastPage(isLastPage: Boolean)

        fun isLoading(isLoading: Boolean)

        fun getYouTubeVideos(moreVideo: Boolean)

        fun getYouTubeVideoData(): YouTubeResponse

        fun addAllItems()

        fun onItemClicked(position: Int, view: android.view.View)
    }
}