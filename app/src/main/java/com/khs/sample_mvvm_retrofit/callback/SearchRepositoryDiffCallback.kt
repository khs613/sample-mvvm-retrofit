package com.khs.sample_mvvm_retrofit.callback

import androidx.recyclerview.widget.DiffUtil
import com.khs.sample_mvvm_retrofit.model.SearchMovie

/**************************************************************************************************
 * Title : Search Repository Diff Callback
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-20 오후 21:49
**************************************************************************************************/

class SearchRepositoryDiffCallback(
    private val oldItem: List<SearchMovie>,
    private val newItem: List<SearchMovie>
    ) : DiffUtil.Callback() {
    override fun getNewListSize(): Int = newItem.size
    override fun getOldListSize(): Int = oldItem.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldItem[oldItemPosition].docid == newItem[newItemPosition].docid
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldItem[oldItemPosition] == newItem[newItemPosition]
}
