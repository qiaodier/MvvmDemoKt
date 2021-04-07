package com.mvvm.kt.entity

/**
 *@author qiaohao
 *@date 21-4-2 上午11:21
 */
open class BaseResp {

    val code:String=""
    val message:String=""

    fun isOk():Boolean{
        return code.equals("1")
    }
}