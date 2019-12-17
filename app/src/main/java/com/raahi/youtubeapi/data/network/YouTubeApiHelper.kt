package com.raahi.youtubeapi.data.network

import com.raahi.youtubeapi.data.network.model.response.CommentsResponse
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class YouTubeApiHelper @Inject constructor(retrofit: Retrofit): ApiHelper {

    private var mApiHelper: ApiHelper = retrofit.create<ApiHelper>(ApiHelper::class.java)

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