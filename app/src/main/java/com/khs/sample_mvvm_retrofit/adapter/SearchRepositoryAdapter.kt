package com.khs.sample_mvvm_retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.khs.sample_mvvm_retrofit.R
import com.khs.sample_mvvm_retrofit.callback.SearchRepositoryDiffCallback
import com.khs.sample_mvvm_retrofit.model.SearchMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**************************************************************************************************
 * Title : Search Repository Adapter
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-19 오후 16:02
**************************************************************************************************/

class SearchRepositoryAdapter(
    private var repositories: List<SearchMovie>
    ) : RecyclerView.Adapter<SearchRepositoryItemHolder>() {
    interface OnSearchRepositoryClickListener {
        fun onItemClick(position: Int)
    }

    var listener: OnSearchRepositoryClickListener? = null

    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRepositoryItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
        R.layout.item_search_layout, parent, false)

        return SearchRepositoryItemHolder(view, listener)
    }

    override fun onBindViewHolder(holder: SearchRepositoryItemHolder, position: Int) {
        holder.bind(repositories[position])
    }

    fun update(updated : List<SearchMovie>) {
        CoroutineScope(Dispatchers.Main).launch {
            val diffResult = async(Dispatchers.IO) {
                getDiffResult(updated)
            }
            repositories = updated
            diffResult.await().dispatchUpdatesTo(this@SearchRepositoryAdapter)
            notifyDataSetChanged()
        }
    }

    private fun getDiffResult(updated: List<SearchMovie>): DiffUtil.DiffResult {
        val diffCallback = SearchRepositoryDiffCallback(repositories, updated)
        return DiffUtil.calculateDiff(diffCallback)
    }

    fun getItem(position: Int) = repositories[position]

}
