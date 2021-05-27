package com.khs.sample_mvvm_retrofit.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khs.sample_mvvm_retrofit.model.SearchMovie
import com.khs.sample_mvvm_retrofit.network.RetrofitConstants
import com.khs.sample_mvvm_retrofit.network.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**************************************************************************************************
 * Title : Main ViewModel
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-19 오후 14:08
**************************************************************************************************/

class MainViewModel(private val searchRepository: RetrofitRepository) : ViewModel() {
    private val searchRepositoryLiveData = MutableLiveData<List<SearchMovie>>()
    val searchRepositories = searchRepositoryLiveData

    private val lottieLoadingLiveData = MutableLiveData<Boolean>(false)
    val lottieLoading = lottieLoadingLiveData

    fun requestSearchRepositories(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                searchRepository.getAllMovies(
                    RetrofitConstants.ServerValue.Collection,
                    RetrofitConstants.ServerValue.ServiceKey,
                    RetrofitConstants.ServerValue.ListCount,
                    RetrofitConstants.ServerValue.Detail, query)?.let { response ->
                    if(response.isSuccessful) {
                        response.body()?.let {
                            searchRepositoryLiveData.postValue(it.data[0].result)
                        }
                    }
                }
            } catch (e: HttpException) {
                lottieLoading.postValue(false)
                e.printStackTrace()
            } catch (e: IOException) {
                lottieLoading.postValue(false)
                e.printStackTrace()
            } catch (e: Throwable) {
                lottieLoading.postValue(false)
                e.printStackTrace()
            }
        }
    }
}