package com.khs.sample_mvvm_retrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.khs.sample_mvvm_retrofit.adapter.SearchRepositoryAdapter
import com.khs.sample_mvvm_retrofit.model.SearchMovie
import com.khs.sample_mvvm_retrofit.network.RetrofitRepository
import com.khs.sample_mvvm_retrofit.viewModel.MainViewModel
import com.khs.sample_mvvm_retrofit.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

/**************************************************************************************************
 * Title : MainActivity
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-19 오전 11:19
**************************************************************************************************/

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var mSearchRepositoryAdapter: SearchRepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButton()
        initViewModel()
    }

    private fun initButton() {
        searchButton.setOnClickListener { onSearchClick() }
    }

    private fun initViewModel() {
        viewModelFactory = MainViewModelFactory(RetrofitRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.searchRepositories.observe(this) {
            updateRepositories(it)
        }

        viewModel.lottieLoading.observe(this) {
            if(it == false) setHideLottie()
            else setShowLottie()
        }
    }

    private fun updateRepositories(repos: List<SearchMovie>) {
        viewModel.lottieLoading.postValue(false)
        if(::mSearchRepositoryAdapter.isInitialized) {
            mSearchRepositoryAdapter.update(repos)
        } else {
            mSearchRepositoryAdapter = SearchRepositoryAdapter(repos).apply {
                listener = object : SearchRepositoryAdapter.OnSearchRepositoryClickListener {
                    override fun onItemClick(position: Int) {
                        mSearchRepositoryAdapter.getItem(position).run {
                           // Todo 추후 recyclerView item 선택 시 구현
                        }
                    }
                }
            }

            recyclerView.run {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = mSearchRepositoryAdapter
            }
        }
    }

    private fun onSearchClick() {
        viewModel.lottieLoading.postValue(true)
        searchInputView.run {
            viewModel.requestSearchRepositories(searchInputView.text.toString())
            hideKeyboard()
        }
    }

    private fun setShowLottie() {
        lottieView.apply {
            visibility = View.VISIBLE
            setAnimation(R.raw.loading_animation)
            repeatCount = -1
            playAnimation()
        }
    }

    private fun setHideLottie() {
        lottieView.apply {
            if(isAnimating) cancelAnimation()
            visibility = View.GONE
        }
    }

    private fun hideKeyboard() {
        currentFocus?.run {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        }
    }
}