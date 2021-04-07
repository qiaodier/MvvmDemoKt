package com.mvvm.kt.mvvm.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.kt.base.IBaseModel
import com.mvvm.kt.base.IBaseView
import com.mvvm.kt.base.IViewModel
import com.mvvm.kt.entity.Login
import com.mvvm.kt.entity.LoginResp
import com.mvvm.kt.entity.NewsResponse
import com.mvvm.kt.http.rx.BaseObserver
import com.mvvm.kt.mvvm.model.LoginModel
import com.mvvm.kt.utils.HttpRequestUtils

/**
 *@author qiaohao
 *@date 21-4-2 上午9:45
 *@desc view层 和 数据层的中间桥梁
 */
class LoginViewModel constructor(model: LoginModel) : IViewModel<IBaseModel, IBaseView>(model) {

    var userName = MutableLiveData<String>()
    var userPwd = MutableLiveData<String>()

    fun realLogin() {
        Log.e("LoginViewModel-realLogin", "${userName.value}  ${userPwd.value}")
        val login = Login(userName.value,userPwd.value)
        HttpRequestUtils
            .applyScheduler((mModel as LoginModel).loginRx(login), mView?.get())
            ?.subscribe(object : BaseObserver<LoginResp>(mView?.get()) {
                override fun onSuccess(t: LoginResp) {
                    // 因为基类已经判断是否ok 此处不用做状态码的判断
                    mView?.get()?.requestSuccess()
                }

                override fun onFailure(errorMsg: String) {
                    mView?.get()?.requestFail(errorMsg)
                }

                override fun onComplete() {
                    mView?.get()?.requestComplete()
                }
            })
    }


    /**
     * 获取新闻列表的网络请求
     */
    fun getNews(url: String):LiveData<NewsResponse>{
        return (mModel as LoginModel).newsLiveData(url)
    }

    /**
     *
     * 自定义factory
     */
    class LoginViewModelFactory(private val model: LoginModel) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(model) as T
        }
    }


}