package com.zk.jetpack_mvvm.base

import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.zk.jetpack_mvvm.R
import com.zk.jetpack_mvvm.ext.getAppViewModel
import com.zk.jetpack_mvvm.util.SettingUtil
import com.zk.jetpack_mvvm.viewmodel.AppViewModel
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.BaseVmDbFragment

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/21
 * 描述　: 你项目中的Fragment基类，在这里实现显示弹窗，吐司，还有自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmFragment例如
 * abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {
 */
abstract class BaseFragment<VM : BaseViewModel,DB:ViewDataBinding> : BaseVmDbFragment<VM,DB>() {

    private var dialog: MaterialDialog? = null

    val appViewModel: AppViewModel by lazy { getAppViewModel() }

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract override fun layoutId(): Int


    abstract override fun initView()

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    abstract override fun lazyLoadData()

    /**
     * 创建观察者 懒加载之后才会触发
     */
    abstract override fun createObserver()


    /**
     * Fragment执行onViewCreated后触发的方法
     */
    override fun initData() {

    }

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        if (dialog == null) {
            dialog = activity?.let {
                MaterialDialog(it)
                    .cancelable(true)
                    .cancelOnTouchOutside(false)
                    .cornerRadius(8f)
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

}