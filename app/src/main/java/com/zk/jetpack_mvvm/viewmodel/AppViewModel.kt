package com.zk.jetpack_mvvm.viewmodel
/**
 * @author zk
 * @time 2020/9/19 22:33
 * @description APP全局的Viewmodel，可以存放公共数据，当他数据改变时，所有监听他的地方都会收到回调,也可以做发送消息
 * 比如 全局可使用的 地理位置信息，账户信息，事件通知等，
 */
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.callback.UnPeekLiveData

class AppViewModel : BaseViewModel() {

    //App主题颜色 大型项目不推荐以这种方式改变主题颜色，比较繁琐，且容易有遗漏某些地方没有设置主题
    var appColor = UnPeekLiveData<Int>()

}