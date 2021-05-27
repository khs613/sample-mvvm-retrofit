package com.khs.sample_mvvm_retrofit.network

/**************************************************************************************************
 * Title : Retrofit Repository
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-19 오후 12:16
**************************************************************************************************/

class RetrofitRepository {
    private val searchClient = RetrofitService.client

    suspend fun getAllMovies(collection: String, serviceKey: String, listCount: String, detail: String, query: String)
    = searchClient?.getAllMovies(collection, serviceKey, listCount, detail, query)
}