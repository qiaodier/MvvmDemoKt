package com.mvvm.kt.mvvm.view

import android.os.Build
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.kt.R
import com.mvvm.kt.base.BaseActivity
import com.mvvm.kt.databinding.ActivityMainBinding
import com.mvvm.kt.mvvm.model.LoginModel
import com.mvvm.kt.mvvm.viewmodel.LoginViewModel
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class LoginActivity : BaseActivity<LoginViewModel>(), LoginView {


    override fun showErrorMsg() {
        toast("showErrorMsg")
    }

    override fun getActivity(): RxAppCompatActivity {
        return this
    }

    override fun layoutResID(): Int {
        return R.layout.activity_main;
    }

    override fun iniViewModel(): LoginViewModel {
        //使用有参数的构造器初始化继承了ViewModel 的 业务viewmodel
        val factory: LoginViewModel.LoginViewModelFactory =
            LoginViewModel.LoginViewModelFactory(LoginModel())
        return ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        //使用无参数的构造器初始化viewmodel
//        return ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(this.application)).get(LoginViewModel::class.java)
    }

    override fun initViews() {
        // datading 手动刷新页面数据
//        val login = Login("name", "qwertyuiop")
//        getDataBinding().setVariable(BR.login, login)
        getDataBinding().loginViewModel = mViewModel
        getDataBinding().lifecycleOwner = this
        button.setOnClickListener {
            // 使用anko库获取页面控件的内容，并拼装Login
//            toast("${editTextTextPersonName.text} ${editTextTextPersonName3.text}")
//            val login1 = Login(editTextTextPersonName.text.toString(),editTextTextPersonName3.text.toString())
            mViewModel?.realLogin()
        }
        // 点击事件调用iewmodel 中的获取新闻列表接口
        button2.setOnClickListener {
            val news =
                mViewModel?.getNews("https://api.apiopen.top/getJoke?page=1&count=2&type=video")
            //给使用livedata 的 newsResponse 实体类设置监听
            news?.observe(this, Observer {
                it.result.forEach { new ->
                    Log.e("LoginActivity", "$new")
                }
            })
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ms.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        ms.setProgress(0)
    }

    private fun getDataBinding(): ActivityMainBinding {
        return mDataBinding as ActivityMainBinding
    }
}