package com.mvvm.kt.base

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

/**
 *@author qiaohao
 *@date 21-4-2 上午10:14
 *
 */
interface IBaseView {


    /**
     * 获取当前activity
     */
    fun getActivity(): RxAppCompatActivity

    /**
     * 显示loading
     */
    fun showLoading()

    /**
     * 隐藏loading
     */
    fun hideLoading()

    /**
     * 请求成功
     */
    fun requestSuccess()

    /**
     * 请求失败
     */
    fun requestFail(failMsg:String)

    /**
     * 请求完成
     */
    fun requestComplete()
}