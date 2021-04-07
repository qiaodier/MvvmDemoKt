package com.mvvm.kt.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference
import java.util.*

/**
 *@author qiaohao
 *@date 21-4-2 上午10:24
 *@desc 继承ViewModel来实现LiveData双向数据绑定
 */
abstract class IViewModel<M:IBaseModel,V: IBaseView>(model: M?):ViewModel() {

    var mModel:M?=null
    var mView:WeakReference<V>?=null

    init {
        mModel=model
    }


    /**
     * 绑定视图
     */
    fun attachView(view:V?){
        this.mView=WeakReference<V>(view)
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
     * 获取视图
     */
    fun getView(): V? {
        return mView?.get()
    }

    /**
     * 释放资源
     */
    fun releaseViewModel(){
        mModel=null
    }


}