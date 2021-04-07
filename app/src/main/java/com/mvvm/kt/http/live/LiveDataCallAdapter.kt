package com.mvvm.kt.http.live

import androidx.lifecycle.LiveData
import com.mvvm.kt.MvvmApplication
import com.mvvm.kt.http.exception.NetworkError
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 *@author qiaohao
 *@date 21-4-7 上午9:48
 *@desc 实现了livedata的calladapter
 */
class LiveDataCallAdapter<T>(private val responseType:Type,private val isApiResponse:Boolean ) : CallAdapter<T,LiveData<T>> {



    override fun adapt(call: Call<T>): LiveData<T> {
        return CustomLiveData<T>(call,isApiResponse)
    }

    override fun responseType(): Type {
       return responseType
    }

    companion object class CustomLiveData<T>(private  val call:Call<T>,private val isApiResponse: Boolean) : LiveData<T>(){
        private val start = AtomicBoolean(false)
        override fun onActive() {
            super.onActive()
            if (start.compareAndSet(false,true)){
                call.enqueue(object :Callback<T>{
                    override fun onFailure(call: Call<T>, t: Throwable) {
                        if (isApiResponse){
                            NetworkError.error(MvvmApplication.instance,t)
                        }else{
                            postValue(null)
                        }

                    }

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        val body = response.body()
                        postValue(body)
                    }

                })
            }
        }
    }
}