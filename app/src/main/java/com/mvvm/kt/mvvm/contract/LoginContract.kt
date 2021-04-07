package com.mvvm.kt.mvvm.contract

import androidx.lifecycle.LiveData
import com.mvvm.kt.base.IBaseModel
import com.mvvm.kt.base.IBaseView
import com.mvvm.kt.entity.Login
import com.mvvm.kt.entity.LoginResp
import com.mvvm.kt.entity.NewsResponse
import io.reactivex.Observable
import retrofit2.http.Url

/**
 *@author qiaohao
 *@date 21-4-2 上午11:07
 */
interface LoginContract {

    interface View : IBaseView{

        fun showErrorMsg()

    }

    interface Model:IBaseModel{

        fun loginRx(login: Login?): Observable<LoginResp>

        fun newsLiveData(url: String): LiveData<NewsResponse>

    }
}