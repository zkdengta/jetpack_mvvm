package com.zk.jetpack_mvvm.netwok

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.zk.jetpack_mvvm.App
import me.hgj.jetpackmvvm.network.BaseNetworkApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

/**
@author: 10154
@date: 2020/9/20
@description:
 */
object NetworkApi : BaseNetworkApi(){

    //封装NetApiService变量 方便直接快速调用
    val service : NetApiService by lazy {
        getApi(NetApiService::class.java,NetApiService.SERVER_URL)
    }

    //Cookies自动持久化 调用 clear 可清空Cookies
    val cookieJar : PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(),SharedPrefsCookiePersistor(App.CONTEXT))
    }

    //缓存信息配置
    private val cache : Cache by lazy {
        val httpCacheDirectory = File(App.CONTEXT.cacheDir,"http_response")
        Cache(httpCacheDirectory,10*1024*1024)
    }


    /**
     * 实现重写父类的setHttpClientBuilder方法，
     * 在这里可以添加拦截器，可以对Builder做任意操作
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
       builder.apply {
           //设置缓存配置
           cache(cache)
           //添加Cookies自动持久化
           cookieJar(cookieJar)
           //添加缓存拦截器
           addInterceptor(CacheInterceptor())
       }
        return builder
    }
}