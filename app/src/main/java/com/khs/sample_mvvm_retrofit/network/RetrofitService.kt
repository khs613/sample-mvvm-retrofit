package com.khs.sample_mvvm_retrofit.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**************************************************************************************************
 * Title : Retrofit Service
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-24 오전 11:20
**************************************************************************************************/

object RetrofitService {
    val client = getRetrofitService(RetrofitConstants.BASE_URL)?.create(RetrofitApi::class.java)

    private fun getRetrofitService(baseUrl: String): Retrofit? = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkHttpClient(AppInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun provideOkHttpClient(
            interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
            .run {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
                addInterceptor(interceptor)
                build()
            }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            val newRequest = request().newBuilder()
                    .addHeader("CONTENT-TYPE", "application/json; charset=UTF-8")
                    .build()
            proceed(newRequest)
        }
    }
}