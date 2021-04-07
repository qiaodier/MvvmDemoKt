package com.mvvm.kt.http.exception

import android.content.Context
import android.widget.Toast
import com.mvvm.kt.http.exception.RetrofitException.retrofitException

/**
 * @author iqiao
 * @desc ：网络统一异常处理
 */
object NetworkError {
    /**
     * @param context 可以用于跳转Activity等操作
     */
    fun error(context: Context?, throwable: Throwable?) {
        val responeThrowable = retrofitException(throwable)
        when (responeThrowable.code) {
            RetrofitException.ERROR.UNKNOWN,
            RetrofitException.ERROR.PARSE_ERROR,
            RetrofitException.ERROR.NETWORD_ERROR,
            RetrofitException.ERROR.HTTP_ERROR,
            RetrofitException.ERROR.SSL_ERROR ->
                Toast.makeText(
                    context,
                    responeThrowable.message,
                    Toast.LENGTH_SHORT
                ).show()
            -1 -> {
            }
            else -> Toast.makeText(context, responeThrowable.message, Toast.LENGTH_SHORT).show()
        }
    }
}