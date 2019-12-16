package com.raahi.youtubeapi.data.network.model.response

import com.google.gson.annotations.SerializedName

class YouTubeResponse {

    /**
     * "kind": "youtube#searchListResponse",
    "etag": etag,
    "nextPageToken": string,
    "prevPageToken": string,
    "regionCode": string,
    "pageInfo": {
    "totalResults": integer,
    "resultsPerPage": integer
    },
    "items": [
    search Resource
    ]
     */

    var kind = ""
    var etag = ""
    var nextPageToken = ""
    var prevPageToken = ""
    var regionCode = ""
    var pageInfo =  PageInfo()
    var list = ArrayList<Item>()

    class PageInfo {
        var totalResults = 0
        var resultsPerPage = 0

    }

    class Item {
        var kind: String? = null
        var etag: String? = null
        var snippet: Snippet? = null

        class Snippet {
            var publishedAt: String? = null
            var channelId: String? = null
            var title: String? = null
            var description: String? = null
            var channelTitle: String? = null
            var categoryId: String? = null
            var liveBroadcastContent: String? = null
            var thumbnails: Thumbnails? = null
            var tags: List<String> =
                ArrayList()

            class Thumbnails {
                @SerializedName("default")
                var default: Default? = null

                class Default {
                    var url: String? = null
                    var width = 0
                    var height = 0

                }
            }
        }
    }
}