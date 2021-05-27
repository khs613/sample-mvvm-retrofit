package com.khs.sample_mvvm_retrofit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khs.sample_mvvm_retrofit.network.RetrofitRepository

/**************************************************************************************************
 * Title : Main ViewModel Factory
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-19 오후 14:33
**************************************************************************************************/

class MainViewModelFactory(private val searchRepository: RetrofitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RetrofitRepository::class.java).newInstance(searchRepository)
    }
}