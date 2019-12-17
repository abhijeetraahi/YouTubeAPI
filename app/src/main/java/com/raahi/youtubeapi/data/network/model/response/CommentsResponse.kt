package com.raahi.youtubeapi.data.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CommentsResponse {

    @SerializedName("kind")
    @Expose
    val kind: String? = null
    @SerializedName("etag")
    @Expose
    val etag: String? = null
    @SerializedName("nextPageToken")
    @Expose
    val nextPageToken: String? = null
    @SerializedName("pageInfo")
    @Expose
    val pageInfo: PageInfo? = null
    @SerializedName("items")
    @Expose
    val items = mutableListOf<Item>()


    class Item {
        @SerializedName("kind")
        @Expose
        var kind: String? = null
        @SerializedName("etag")
        @Expose
        var etag: String? = null
        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("snippet")
        @Expose
        var snippet: Snippet? = null

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
        @SerializedName("videoId")
        @Expose
        var videoId: String? = null
        @SerializedName("topLevelComment")
        @Expose
        var topLevelComment: TopLevelComment? = null
        @SerializedName("canReply")
        @Expose
        var canReply: Boolean? = null
        @SerializedName("totalReplyCount")
        @Expose
        var totalReplyCount: Int? = null
        @SerializedName("isPublic")
        @Expose
        var isPublic: Boolean? = null

    }

    class Snippet_ {
        @SerializedName("authorDisplayName")
        @Expose
        var authorDisplayName: String? = null
        @SerializedName("authorProfileImageUrl")
        @Expose
        var authorProfileImageUrl: String? = null
        @SerializedName("authorChannelUrl")
        @Expose
        var authorChannelUrl: String? = null
        @SerializedName("videoId")
        @Expose
        var videoId: String? = null
        @SerializedName("textDisplay")
        @Expose
        var textDisplay: String? = null
        @SerializedName("textOriginal")
        @Expose
        var textOriginal: String? = null
        @SerializedName("canRate")
        @Expose
        var canRate: Boolean? = null
        @SerializedName("viewerRating")
        @Expose
        var viewerRating: String? = null
        @SerializedName("likeCount")
        @Expose
        var likeCount: Int? = null
        @SerializedName("publishedAt")
        @Expose
        var publishedAt: String? = null
        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

    }

    class TopLevelComment {
        @SerializedName("kind")
        @Expose
        var kind: String? = null
        @SerializedName("etag")
        @Expose
        var etag: String? = null
        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("snippet")
        @Expose
        var snippet: Snippet_? = null

    }
}