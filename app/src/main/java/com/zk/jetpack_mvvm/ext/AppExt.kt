package com.zk.jetpack_mvvm.ext

import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.Utils
import com.zk.jetpack_mvvm.App
import com.zk.jetpack_mvvm.viewmodel.AppViewModel

/**
@author: 10154
@date: 2020/9/19
@description:
 */

fun AppCompatActivity.getAppViewModel() : AppViewModel{
    (Utils.getApp() as App).let{
        return it.getAppViewModelProvider().get(AppViewModel::class.java)
    }
}
