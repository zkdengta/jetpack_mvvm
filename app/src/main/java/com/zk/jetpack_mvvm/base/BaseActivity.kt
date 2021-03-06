package com.zk.jetpack_mvvm.base

import android.content.res.Resources
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.zk.jetpack_mvvm.R
import com.zk.jetpack_mvvm.ext.getAppViewModel
import com.zk.jetpack_mvvm.util.SettingUtil
import com.zk.jetpack_mvvm.viewmodel.AppViewModel
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.BaseVmDbActivity
import me.jessyan.autosize.AutoSizeCompat


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
                    .customView(R.layout.layout_loading)
                    .lifecycleOwner(this)
            }
            dialog?.getCustomView()?.run {
                this.findViewById<TextView>(R.id.loading_tips).text = message
                appViewModel.appColor.value?.let {
                    this.findViewById<ProgressBar>(R.id.progressBar).indeterminateTintList =
                        SettingUtil.getOneColorStateList(it)
                }
            }
        }
        dialog?.show()
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dialog?.dismiss()
    }

    /**
     * 吐司
     */
    override fun showToast(message: String) {
        ToastUtils.showShort(message)
    }

    /**
     * 在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
     */
    override fun getResources(): Resources {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }


}