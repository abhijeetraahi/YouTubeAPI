package com.raahi.youtubeapi.data

import com.raahi.youtubeapi.data.network.ApiHelper
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import io.reactivex.Observable
import javax.inject.Inject

class YouTubeAPIDataManager @Inject constructor(apiHelper: ApiHelper): DataManager{

    private var mApiHelper: ApiHelper = apiHelper

    override fun getYouTubeData(): Observable<List<YouTubeResponse>> {
        return mApiHelper.getYouTubeData()
    }

}
