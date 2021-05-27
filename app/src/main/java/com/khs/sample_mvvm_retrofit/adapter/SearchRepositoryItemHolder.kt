package com.khs.sample_mvvm_retrofit.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khs.sample_mvvm_retrofit.util.GlideApp
import com.khs.sample_mvvm_retrofit.R
import com.khs.sample_mvvm_retrofit.model.SearchMovie
import kotlinx.android.synthetic.main.item_search_layout.view.*

/**************************************************************************************************
 * Title : Search Repository Item Holder
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-05-19 오후 15:55
**************************************************************************************************/

class SearchRepositoryItemHolder(
    view: View,
    listener: SearchRepositoryAdapter.OnSearchRepositoryClickListener?
) : RecyclerView.ViewHolder(view) {
    private val imageView: ImageView = view.itemImageView
    private val titleView: TextView = view.itemTitleView
    private val directorView: TextView = view.itemDirectorView
    private val dateView: TextView = view.itemDateView

    init {
        view.setOnClickListener {
            listener?.onItemClick(adapterPosition)
        }
    }

    fun bind(model: SearchMovie) {
        model.run {
            val arr = posters.split("|")
            imageView.loadUrl(arr[0])
            titleView.text = title.trim()
            directorView.text = directors.director[0].directorNm
            dateView.text = prodYear
        }
    }

    private fun ImageView.loadUrl(imageUrl: String?) {
        GlideApp.with(this.context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .centerCrop()
            .into(this)
    }
}
