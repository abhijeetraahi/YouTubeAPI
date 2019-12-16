package com.raahi.youtubeapi.ui.home

import com.raahi.youtubeapi.data.DataManager
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse1
import com.raahi.youtubeapi.ui.base.BasePresenter
import com.raahi.youtubeapi.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomePresenter<V : HomeContract.View> @Inject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable),
    HomeContract.Presenter<V> {

    private lateinit var mResponse: YouTubeResponse1

    override fun onAttach(view: V) {
        super.onAttach(view)
        getYouTubeVideos()
    }

    override fun getYouTubeVideos(){

        view?.showProgressView()

        ////?part=snippet&type=video&maxResults=10&key=AIzaSyAVfjiknJE0yE7MQn20Rv1QcBRSxJcE9yQ
        compositeDisposable.add(
            dataManager.getYouTubeData("snippet", "video", "10", "AIzaSyAVfjiknJE0yE7MQn20Rv1QcBRSxJcE9yQ")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    mResponse = response
                    if (mResponse.items.size > 0) {
                        view?.setUpRecyclerView()
                    }else
                        view?.showToast("No data found")

                    view?.hideProgressView()

                }, {
                    it.message?.let { it1 -> view?.showToast(it1) }

                    view?.hideProgressView()
                })
        )
    }

    override fun getYouTubeVideoData(): YouTubeResponse1 {
        return mResponse
    }
}