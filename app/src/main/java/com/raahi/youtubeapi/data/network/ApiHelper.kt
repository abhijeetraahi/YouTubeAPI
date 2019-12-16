package com.raahi.youtubeapi.data.network

import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse1
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {

    @GET("search")
    fun getYouTubeData(
        @Query("part") part: String,
        @Query("type") type: String,
        @Query("maxResults") maxResults: String,
        @Query("key") key: String
        ): Observable<YouTubeResponse1>

    //?part=snippet&type=video&maxResults=10&key=AIzaSyAVfjiknJE0yE7MQn20Rv1QcBRSxJcE9yQ

    @GET("search")
    fun getVideos(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: String,
        @Query("pageToken") pageToken: String
    ): Call<YouTubeResponse>

}