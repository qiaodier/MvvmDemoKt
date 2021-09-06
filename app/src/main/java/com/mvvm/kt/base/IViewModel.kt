package com.mvvm.kt.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference
import java.util.*

/**
 *@author qiaohao
 *@date 21-4-2 上午10:24
 *@desc 继承ViewModel来实现LiveData双向数据绑定
 */
abstract class IViewModel:ViewModel() {


    var mView:WeakReference<in IBaseView>?=null

    init {
    }


    /**
     * 绑定视图
     */
    fun attachView(view:IBaseView?){
        this.mView=WeakReference<IBaseView>(view)
    }

    /**
     * 解绑
     */
    fun detachVew(){
        mView.let {
            it?.clear()
        }
    }


    /**
     * 释放资源
     */
    fun releaseViewModel(){
    }


}