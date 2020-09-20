package com.zk.jetpack_mvvm.netwok

import com.zk.jetpack_mvvm.App
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
/**
 * @author 10154
 * @time 2020/9/20 10:08
 * @description 缓存拦截器
 */

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetWorkUtils.isNetworkAvailable(App.CONTEXT)) {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (!NetWorkUtils.isNetworkAvailable(App.CONTEXT)) {
            val maxAge = 60 * 60
            response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        } else {
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
        return response
    }
}