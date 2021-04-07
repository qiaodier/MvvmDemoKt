package com.mvvm.kt

import android.app.Application
import android.content.Context
import com.mvvm.kt.utils.CustomLogCatStrategy
import com.orhanobut.logger.AndroidLogAdapter

import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 *@author qiaohao
 *@date 21-4-2 下午8:45
 */
class MvvmApplication : Application() {

    init {
        instance = this
    }
    companion object{
        lateinit var instance:MvvmApplication
    }

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    fun initLogger(){
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)  // 输出线程信息. 默认输出
            .methodCount(0)         // 方法栈打印的个数，默认是2
            .methodOffset(7)        // 设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
            .logStrategy(CustomLogCatStrategy())
            .tag("okHttp-request-")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Logger.e("MvvmApplication ==> "+"Logger init  complete!")
    }
}