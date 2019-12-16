package com.raahi.youtubeapi.data

import com.raahi.youtubeapi.data.network.ApiHelper
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse1
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject

class YouTubeAPIDataManager @Inject constructor(apiHelper: ApiHelper): DataManager{

    private var mApiHelper: ApiHelper = apiHelper

    override fun getYouTubeData(
        part: String,
        type: String,
        maxResults: String,
        key: String
    ): Observable<YouTubeResponse1> {
        return mApiHelper.getYouTubeData(part, type, maxResults, key)
    }

    override fun getVideos(
        query: String,
        type: String,
        key: String,
        part: String,
        maxResults: String,
        pageToken: String
    ): Call<YouTubeResponse> {
        return mApiHelper.getVideos(query, type, key, part, maxResults, pageToken)
    }


}
