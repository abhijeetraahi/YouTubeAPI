package com.raahi.youtubeapi.ui.details

import com.raahi.youtubeapi.YouTubeAPIApplication
import com.raahi.youtubeapi.data.DataManager
import com.raahi.youtubeapi.data.network.model.VideoInfo
import com.raahi.youtubeapi.data.network.model.response.CommentsResponse
import com.raahi.youtubeapi.di.components.DaggerPresenterComponent
import com.raahi.youtubeapi.di.modules.PresenterModule
import com.raahi.youtubeapi.util.Const
import com.raahi.youtubeapi.util.rx.SchedulerProvider
import com.raahi.youtubeapi.util.rx.YouTubeAPISchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsPresenter(
    private val view: DetailsContract.View,
    val context: YouTubeAPIApplication
) : DetailsContract.Presenter {

    lateinit var dataManager: DataManager
        @Inject set
    lateinit var schedulerProvider: SchedulerProvider
        @Inject set
    lateinit var compositeDisposable: CompositeDisposable
        @Inject set

    private lateinit var mResponse: CommentsResponse
    private lateinit var mVideoInfo: VideoInfo

    override fun onAttach() {

        schedulerProvider = YouTubeAPISchedulerProvider()

        compositeDisposable = CompositeDisposable()

        DaggerPresenterComponent.builder().presenterModule(PresenterModule())
            .applicationComponent(context.appComponent)
            .build().inject(this)

        view.initView()
        getComments()
    }

    override fun setVideoInfo(videoInfo: VideoInfo) {
        this.mVideoInfo = videoInfo

    }

    override fun getVideoInfo(): VideoInfo {
        return mVideoInfo
    }

    override fun getComments() {

        compositeDisposable.add(
            dataManager.getCommentsResponse(Const.PART, Const.MAX_RESULT, Const.KEY, mVideoInfo.videoId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    mResponse = response
                    if (mResponse.items.isNotEmpty())
                        view.setUpRecyclerView()
                    else
                        view.showToast("No comments found!")
                    view.hideProgressView()

                }, {
                    it.message?.let { it1 -> view.showToast(it1) }
                    view.hideProgressView()
                })
        )
    }

    override fun getCommentResponse(): CommentsResponse {
        return mResponse
    }
}