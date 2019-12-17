package com.raahi.youtubeapi.ui.home

import android.view.View
import com.raahi.youtubeapi.data.DataManager
import com.raahi.youtubeapi.data.network.model.VideoInfo
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import com.raahi.youtubeapi.ui.base.BasePresenter
import com.raahi.youtubeapi.util.Const
import com.raahi.youtubeapi.util.PaginationListener
import com.raahi.youtubeapi.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter<V : HomeContract.View> @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable),
    HomeContract.Presenter<V> {

    private lateinit var mResponse: YouTubeResponse
    private lateinit var newResponse: YouTubeResponse
    private var pageToken = ""

    private var currentPage = PaginationListener.PAGE_START
    private var isLastPage = false
    private var totalPage = 10
    private var isLoading = false
    private var itemCount = 0

    override fun onAttach(view: V) {
        super.onAttach(view)
        view.initToolBar()
        view.initView()
        view.scheduleJob()
        getYouTubeVideos(false)
    }

    override fun getYouTubeVideos(moreVideo: Boolean){
        compositeDisposable.add(
            dataManager.getYouTubeData(Const.PART, Const.TYPE, Const.MAX_RESULT, Const.KEY, pageToken)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->

                    if (moreVideo) {
                        newResponse = response
                    }else
                        mResponse = response

                    if (mResponse.items.isNotEmpty() && !moreVideo) {
                        view?.setUpRecyclerView()
                        pageToken = mResponse.nextPageToken!!
                    }else if (moreVideo){
                        view?.notifyRecyclerView()
                        pageToken = mResponse.nextPageToken!!
                    }
                    else
                        view?.showToast("No data found")

                    view?.hideProgressView()

                }, {
                    it.message?.let { it1 -> view?.showToast(it1) }
                    view?.hideProgressView()
                })
        )
    }

    override fun getYouTubeVideoData(): YouTubeResponse {
        return mResponse
    }

    override fun addAllItems() {
        mResponse.items.addAll(newResponse.items)
    }

    override fun onItemClicked(position: Int, view: View) {
        val videoInfo = VideoInfo()
        videoInfo.videoId = mResponse.items[position].id?.videoId!!
        videoInfo.title = mResponse.items[position].snippet?.title!!
        videoInfo.channelName = mResponse.items[position].snippet?.channelTitle!!
        this.view?.launchDetailsActivity(videoInfo, view)
    }

    override fun getCurrentPage(): Int {
        return currentPage
    }

    override fun getTotalPage(): Int {
        return totalPage
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun isLastPage(): Boolean {
        return isLastPage
    }

    override fun isLoading(): Boolean {
        return isLoading
    }

    override fun increaseCurrentPage() {
        this.currentPage++
    }

    override fun setTotalPage(totalPage: Int) {
        this.totalPage = totalPage
    }

    override fun setItemCount(itemCount: Int) {
        this.itemCount = itemCount
    }

    override fun isLastPage(isLastPage: Boolean) {
        this.isLastPage = isLastPage
    }

    override fun isLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    override fun setCurrentPage(currentPage: Int) {
        this.currentPage = currentPage
    }
}