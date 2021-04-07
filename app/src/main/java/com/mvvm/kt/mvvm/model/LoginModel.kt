package com.mvvm.kt.mvvm.model

import androidx.lifecycle.LiveData
import com.mvvm.kt.entity.Login
import com.mvvm.kt.entity.LoginResp
import com.mvvm.kt.entity.NewsResponse
import com.mvvm.kt.http.RequestCreator
import com.mvvm.kt.mvvm.contract.LoginContract
import com.mvvm.kt.mvvm.model.api.LoginApi
import io.reactivex.Observable

/**
 *@author qiaohao
 *@date 21-4-2 下午12:20
 */
class LoginModel:LoginContract.Model {

    override fun loginRx(login: Login?): Observable<LoginResp> {
        // return  网络请求
        return RequestCreator.instance.get(LoginApi::class.java).loginRx(login)
    }

    override fun newsLiveData(url: String): LiveData<NewsResponse> {
        return RequestCreator.instance.get(LoginApi::class.java).newsLiveData(url)
    }

}