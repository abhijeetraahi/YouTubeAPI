package com.raahi.youtubeapi.data.network

import com.raahi.youtubeapi.data.network.model.response.CommentsResponse
import com.raahi.youtubeapi.data.network.model.response.YouTubeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {

    @GET("search")
    fun getYouTubeData(
        @Query("part") part: String,
        @Query("type") type: String,
        @Query("maxResults") maxResults: String,
        @Query("key") key: String,
        @Query("pageToken") pageToken: String
        ): Observable<YouTubeResponse>

    @GET("commentThreads")
    fun getCommentsResponse(
        @Query("part") part: String,
        @Query("maxResults") maxResults: String,
        @Query("key") key: String,
        @Query("videoId") videoId: String
    ): Observable<CommentsResponse>
}