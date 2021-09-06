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
import com.mvvm.kt.mvvm.view.LoginView
import com.mvvm.kt.utils.HttpRequestUtils

/**
 *@author qiaohao
 *@date 21-4-2 上午9:45
 *@desc view层 和 数据层的中间桥梁
 */
class LoginViewModel constructor(private val model: LoginModel,private val view:LoginView) : IViewModel() {

    init {

    }

    var userName = MutableLiveData<String>()
    var userPwd = MutableLiveData<String>()


    fun realLogin() {
        Log.e("LoginViewModel-realLogin", "${userName.value}  ${userPwd.value}")
        val login = Login(userName.value,userPwd.value)
        HttpRequestUtils
            .applyScheduler(model.loginRx(login), view)
            ?.subscribe(object : BaseObserver<LoginResp>(view) {
                override fun onSuccess(t: LoginResp) {
                    // 因为基类已经判断是否ok 此处不用做状态码的判断
                    view.requestSuccess()
                }

                override fun onFailure(errorMsg: String) {
                    view.requestFail(errorMsg)
                }

                override fun onComplete() {
                    view.requestComplete()
                }
            })
    }


    /**
     * 获取新闻列表的网络请求
     */
    fun getNews(url: String):LiveData<NewsResponse>{
        return model.newsLiveData(url)
    }

    /**
     *
     * 自定义factory viewModel 构造器传参用
     */
    class LoginViewModelFactory(private val model: LoginModel,private val view:LoginView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(model,view) as T
        }
    }


}