package com.khs.sample_mvvm_retrofit.network

/**************************************************************************************************
 * Title : Retrofit Constants
 * Description : network에 필요한 정보 정의
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-21 오후 23:50
**************************************************************************************************/

object RetrofitConstants {
    var BASE_URL = "http://api.koreafilm.or.kr/"

    /**
     * API PATH
     */
    const val API_SEARCH = "openapi-data2/wisenut/search_api/search_json2.jsp"

    /**
     * Server Field 정보 정의
     */
    object ServerField {
        const val Collection = "collection"
        const val ServiceKey = "ServiceKey"
        const val ListCount = "listCount"
        const val Detail = "detail"
        const val Query = "query"
    }

    /**
     * Server 고정 값 정의
     */
    object ServerValue {
        const val Collection= "kmdb_new2"
        const val ServiceKey = "648YD1DY87443AEN6ED9"
        const val ListCount = "100"
        const val Detail = "Y"
    }
}