package com.zk.jetpack_mvvm.base

import androidx.databinding.ViewDataBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.zk.jetpack_mvvm.ext.getAppViewModel
import com.zk.jetpack_mvvm.viewmodel.AppViewModel
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.BaseVmDbActivity


abstract  class BaseActivity<VM : BaseViewModel,DB : ViewDataBinding> :BaseVmDbActivity<VM,DB>() {
    private var dialog: MaterialDialog? = null

    val appViewModel: AppViewModel by lazy { getAppViewModel() }

    abstract override  fun layoutId():Int

    abstract override fun initView()

    /**
     * 创建观察者
     */
    abstract override fun createObserver()

    /**
     * 显示加载框
     */
    override fun showLoading(message : String){
        if (dialog == null){
            dialog = this.let {
                MaterialDialog(it)
                    .cancelable(true)
                    .cancelOnTouchOutside(false)
                    .customView()
                    .lifecycleOwner(this)
            }
        }
        dialog?.show()
    }


}