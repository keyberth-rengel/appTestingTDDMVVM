package com.every.apptestingtddmvvm.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.every.apptestingtddmvvm.R
import com.every.apptestingtddmvvm.application.AppConstants.BASE_IMAGE_URL
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity
import com.every.apptestingtddmvvm.databinding.RowTableBinding
import com.every.apptestingtddmvvm.ui.listener.OnClickListener


class ViewHolderTable(view: View) : RecyclerView.ViewHolder(view) {

    private val mBinding = RowTableBinding.bind(view)

    fun setValueInRowTable(
        post: PostEntity,
        onClickListener: OnClickListener
    ) {

        mBinding.title.text = post.originalTitle
        mBinding.voteAverage.text = post.voteAverage.toString()
        mBinding.createAt.text = post.releaseDate.split('T')[0]
        mBinding.description.text = post.overview
        mBinding.picture.load("${BASE_IMAGE_URL}${post.posterPath}") {
            crossfade(true)
            placeholder(R.drawable.ic_hide_image)
        }
        mBinding.containerRow.setOnClickListener {
            onClickListener.onClickListener(post)
        }
    }
}