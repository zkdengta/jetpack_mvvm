package com.zk.jetpack_mvvm.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.Utils
import com.zk.jetpack_mvvm.App
import com.zk.jetpack_mvvm.viewmodel.AppViewModel
import me.hgj.jetpackmvvm.Ktx

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

fun Fragment.getAppViewModel(): AppViewModel {
    (Ktx.app as App).let {
        return it.getAppViewModelProvider().get(AppViewModel::class.java)
    }
}
