package com.zk.jetpack_mvvm.util

import android.content.res.ColorStateList

/**
@author: 10154
@date: 2020/9/20
@description:
 */
object SettingUtil {

    fun getOneColorStateList(color: Int): ColorStateList {
        val colors = intArrayOf(color)
        val states = arrayOfNulls<IntArray>(1)
        states[0] = intArrayOf()
        return ColorStateList(states, colors)
    }
    
}