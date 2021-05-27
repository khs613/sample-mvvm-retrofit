package com.khs.sample_mvvm_retrofit.model

import com.google.gson.annotations.SerializedName

/**************************************************************************************************
 * Title : Search Response Model
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-21 오후 23:02
**************************************************************************************************/

data class SearchResponseModel (
    @SerializedName("Query")
    val query: String,

    @SerializedName("KMAQuery")
    val kmaQuery: String,

    @SerializedName("TotalCount")
    val totalCount: Int,

    @SerializedName("Data")
    val data: List<SearchResultResponse>
)

data class SearchResultResponse (
    @SerializedName("Result")
    val result: List<SearchMovie>
)

data class SearchMovie (
    @SerializedName("DOCID")
    val docid: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("prodYear")
    val prodYear: String,

    @SerializedName("posters")
    val posters: String,

    @SerializedName("directors")
    val directors: SearchDirectors
)

data class SearchDirectors (
    @SerializedName("director")
    val director: List<SearchDirector>,
)

data class SearchDirector (
    @SerializedName("directorNm")
    val directorNm: String
)