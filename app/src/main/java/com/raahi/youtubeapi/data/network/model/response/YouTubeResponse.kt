package com.raahi.youtubeapi.data.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class YouTubeResponse {

    @SerializedName("kind")
    @Expose
    val kind: String? = null
    @SerializedName("etag")
    @Expose
    val etag: String? = null
    @SerializedName("nextPageToken")
    @Expose
    val nextPageToken: String? = null
    @SerializedName("regionCode")
    @Expose
    val regionCode: String? = null
    @SerializedName("pageInfo")
    @Expose
    val pageInfo: PageInfo? = null
    @SerializedName("items")
    @Expose
    val items = mutableListOf<Item>()

    class Default {
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("width")
        @Expose
        var width: Int? = null
        @SerializedName("height")
        @Expose
        var height: Int? = null

    }

    class High {
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("width")
        @Expose
        var width: Int? = null
        @SerializedName("height")
        @Expose
        var height: Int? = null

    }

    class Id {
        @SerializedName("kind")
        @Expose
        var kind: String? = null
        @SerializedName("videoId")
        @Expose
        var videoId: String? = null

    }

    class Item {
        @SerializedName("kind")
        @Expose
        var kind: String? = null
        @SerializedName("etag")
        @Expose
        var etag: String? = null
        @SerializedName("id")
        @Expose
        var id: Id? = null
        @SerializedName("snippet")
        @Expose
        var snippet: Snippet? = null

    }

    class Medium {
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("width")
        @Expose
        var width: Int? = null
        @SerializedName("height")
        @Expose
        var height: Int? = null

    }

    class PageInfo {
        @SerializedName("totalResults")
        @Expose
        var totalResults: Int? = null
        @SerializedName("resultsPerPage")
        @Expose
        var resultsPerPage: Int? = null

    }

    class Snippet {
        @SerializedName("publishedAt")
        @Expose
        var publishedAt: String? = null
        @SerializedName("channelId")
        @Expose
        var channelId: String? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("thumbnails")
        @Expose
        var thumbnails: Thumbnails? = null
        @SerializedName("channelTitle")
        @Expose
        var channelTitle: String? = null
        @SerializedName("liveBroadcastContent")
        @Expose
        var liveBroadcastContent: String? = null

    }

    class Thumbnails {
        @SerializedName("default")
        @Expose
        var default: Default? = null
        @SerializedName("medium")
        @Expose
        var medium: Medium? = null
        @SerializedName("high")
        @Expose
        var high: High? = null

    }
}