package com.mvvm.kt.base

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import org.jetbrains.anko.toast

/**
 *@author qiaohao
 *@date 21-4-2 上午9:59
 *@desc baseActivity Activity的基类
 */
abstract class BaseActivity<T : IViewModel<IBaseModel,IBaseView>> :
    RxAppCompatActivity(), LifecycleObserver,
    IBaseView {

    var mViewModel: T? = null
    var mDataBinding: ViewDataBinding?=null

    /**
     * 资源文件id
     */
    abstract fun layoutResID(): Int

    /**
     * 初始化ViewModel
     */
    abstract fun iniViewModel(): T

    /**
     * 初始化组件
     */
    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this,layoutResID())
        lifecycle.addObserver(this)
        mViewModel = iniViewModel()
        mViewModel?.attachView(this)
        initViews()

    }


    //region lifecyler生命周期回调
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(owner: LifecycleOwner?) {
        Log.i(this.javaClass.name, "onCreate()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart(owner: LifecycleOwner?) {
        Log.i(this.javaClass.name, "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(owner: LifecycleOwner?) {
        Log.i(this.javaClass.name, "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause(owner: LifecycleOwner?) {
        Log.i(this.javaClass.name, "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop(owner: LifecycleOwner?) {
        Log.i(this.javaClass.name, "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy(owner: LifecycleOwner?) {
        Log.i(this.javaClass.name, "onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun onAny(owner: LifecycleOwner?) {
        Log.i(this.javaClass.name, "onAny")
    }
    //endregion
    override fun showLoading() {
        toast("showLoading")
    }


    override fun hideLoading() {
        toast("hideLoading")
    }

    override fun requestSuccess() {
        toast("requestSuccess")
    }

    override fun requestFail(failMsg: String) {
        toast("requestSuccess  $failMsg")
    }

    override fun requestComplete() {
        toast("requestComplete")
    }


}