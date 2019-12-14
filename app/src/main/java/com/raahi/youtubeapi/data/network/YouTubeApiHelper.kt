package com.raahi.youtubeapi.data.network

import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class YouTubeApiHelper @Inject constructor(retrofit: Retrofit): ApiHelper {

    private var mApiHelper: ApiHelper = retrofit.create<ApiHelper>(ApiHelper::class.java)

    override fun getYouTubeData(): Observable<List<YouTubeResponse>> {
        return mApiHelper.getYouTubeData()
    }

}