package com.mvvm.kt.utils

import com.mvvm.kt.base.IBaseView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by iqiao on 2020-03-05 09:07
 * Desc: 网络工具封装类
 *
 * @author iqiao
 */
object HttpRequestUtils {
    /**
     * 该方法封装线程执行和绑定生命周期
     *
     * @param observable
     * @param baseView
     * @param <T>
     * @return
    </T> */
    fun <T> applyScheduler(
        observable: Observable<T>?,
        baseView: IBaseView?
    ): Observable<T>? {
        return observable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.compose(baseView?.getActivity()?.bindToLifecycle())
    }
}