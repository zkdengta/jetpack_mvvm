package com.zk.jetpack_mvvm

import android.content.Context
import me.hgj.jetpackmvvm.BaseApp
import kotlin.properties.Delegates

/**
@author: 10154
@date: 2020/9/19
@description:
 */
class App : BaseApp(){

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

}