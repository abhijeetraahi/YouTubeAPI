package com.raahi.youtubeapi.data

import com.raahi.youtubeapi.data.network.ApiHelper
import com.raahi.youtubeapi.data.network.model.response.CommentsResponse
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import io.reactivex.Observable
import javax.inject.Inject

class YouTubeAPIDataManager @Inject constructor(apiHelper: ApiHelper): DataManager{

    private var mApiHelper: ApiHelper = apiHelper

    override fun getYouTubeData(
        part: String,
        type: String,
        maxResults: String,
        key: String,
        pageToken: String
    ): Observable<YouTubeResponse> {
        return mApiHelper.getYouTubeData(part, type, maxResults, key, pageToken)
    }

    override fun getCommentsResponse(
        part: String,
        maxResults: String,
        key: String,
        videoId: String
    ): Observable<CommentsResponse> {
        return mApiHelper.getCommentsResponse(part, maxResults, key, videoId)
    }

}
