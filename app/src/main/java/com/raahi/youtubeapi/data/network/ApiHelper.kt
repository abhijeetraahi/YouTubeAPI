package com.raahi.youtubeapi.data.network

import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiHelper {

    @GET("posts")
    fun getYouTubeData(): Observable<List<YouTubeResponse>>

}