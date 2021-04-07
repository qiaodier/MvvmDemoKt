package com.mvvm.kt.http.rx

import com.mvvm.kt.base.IBaseView
import com.mvvm.kt.entity.BaseResp
import com.mvvm.kt.http.exception.NetworkError
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.util.*
import java.util.function.Consumer

/**
 *@author qiaohao
 *@date 21-4-2 下午3:01
 */
abstract class BaseObserver<T:BaseResp> constructor(iBaseView: IBaseView?) : Observer<T> {


    protected abstract fun onSuccess(t: T)

    protected abstract fun onFailure(errorMsg: String)


    var mBaseView: WeakReference<IBaseView>? = null

    init {
        mBaseView = WeakReference(iBaseView!!)
    }

    override fun onSubscribe(d: Disposable) {
        Optional.ofNullable(mBaseView).ifPresent(
            Consumer {
                it.get()?.showLoading()
            }
        )
    }

    override fun onNext(t: T) {
        if (t.isOk()) {
            onSuccess(t)
            return
        }
        onFailure(t.message + "")
    }

    override fun onError(e: Throwable) {
        Optional.ofNullable(mBaseView).ifPresent(
            Consumer {
                it.get()?.hideLoading()
            }
        )
        onNetError(e)
    }


    private fun onNetError(e: Throwable) {
        Optional.ofNullable(mBaseView).ifPresent(
            Consumer {
                NetworkError.error(mBaseView?.get()?.getActivity()?.applicationContext,e)
            }
        )
    }
}