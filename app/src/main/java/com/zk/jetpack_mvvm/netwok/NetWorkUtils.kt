package com.zk.jetpack_mvvm.netwok

import android.content.Context
import android.net.ConnectivityManager
/**
 * @author 10154
 * @time 2020/9/20 10:09
 * @description
 */
class NetWorkUtils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}