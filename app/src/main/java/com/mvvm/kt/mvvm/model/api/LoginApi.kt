package com.mvvm.kt.mvvm.model.api

import androidx.lifecycle.LiveData
import com.mvvm.kt.entity.Login
import com.mvvm.kt.entity.LoginResp
import com.mvvm.kt.entity.NewsResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 *@author qiaohao
 *@date 21-4-2 下午2:44
 *@desc 目前只支持两种数据回调处理
 *      RxJava
 *      LiveData
 */
interface LoginApi {


    @POST("user/login")
    fun loginRx(@Body login: Login?):Observable<LoginResp>


    @GET
    fun newsLiveData(@Url url: String):LiveData<NewsResponse>

}