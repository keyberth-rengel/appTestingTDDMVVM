package com.every.apptestingtddmvvm.ui.listener

import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity

interface OnClickListener {
    fun onClickListener(post: PostEntity)
}