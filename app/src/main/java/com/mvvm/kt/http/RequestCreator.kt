package com.mvvm.kt.http

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mvvm.kt.BuildConfig
import com.mvvm.kt.http.live.LiveDataCallAdapter
import com.mvvm.kt.http.live.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *@author qiaohao
 *@date 21-4-2 下午1:49
 */
class RequestCreator private constructor() {

    /**
     * 执行在构造器之前
     */
    init {
        initOk()
    }

    private val CONNECT_TIMEOUT = 10;
    private val READ_TIMEOUT = 10;
    private var mRetrofit: Retrofit? = null

    /**
     * 静态内部类实现单例
     */
    companion object {
        val instance = SingletonHolder.holder
        const val BASE_URL = "https://www.baidu.com/"
    }

    private object SingletonHolder {
        val holder = RequestCreator()
    }

    /**
     * 外部设置是否打印日志
     */
//    fun setLogEnable(logFlag: Boolean) {
//        LOG_ENABLE = logFlag
//    }

    /**
     * 外部设置基础请求地址
     */
//    fun setBaseUrl(baseUrl:String){
//        BASE_URL= baseUrl
//    }

    /**
     * 构建请求真实对象
     */
    fun <T> get(service: Class<T>): T {
        return mRetrofit!!.create(service)
    }

    /**
     * 初始化okHttp+Retrofit
     */
    private fun initOk() {
        var httpLog = HttpLoggingInterceptor(HttpLog())
        httpLog.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addNetworkInterceptor(httpLog)
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
                //使用rxjava处理网络响应数据
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用livedata处理网络响应数据
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

}