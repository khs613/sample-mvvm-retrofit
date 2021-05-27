package com.khs.sample_mvvm_retrofit.network

import com.khs.sample_mvvm_retrofit.model.SearchResponseModel
import retrofit2.Response
import retrofit2.http.*

/**************************************************************************************************
 * Title : Retrofit Api
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-19 오전 11:49
**************************************************************************************************/

interface RetrofitApi {
    @GET(RetrofitConstants.API_SEARCH)
    suspend fun getAllMovies(
        @Query(RetrofitConstants.ServerField.Collection) collection: String,
        @Query(RetrofitConstants.ServerField.ServiceKey) serviceKey: String,
        @Query(RetrofitConstants.ServerField.ListCount) listCount: String,
        @Query(RetrofitConstants.ServerField.Detail) detail: String,
        @Query(RetrofitConstants.ServerField.Query) query: String
    ) : Response<SearchResponseModel>
}