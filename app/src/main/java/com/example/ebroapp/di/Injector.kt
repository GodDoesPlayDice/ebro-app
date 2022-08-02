package com.example.ebroapp.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ebroapp.di.component.ActivityComponent
import com.example.ebroapp.di.component.DaggerAppComponent
import com.example.ebroapp.di.component.IAppComponent
import com.example.ebroapp.di.module.ActivityModule
import com.example.ebroapp.di.module.AppModule
import com.example.ebroapp.receiver.ActionPowerReceiver
import com.example.ebroapp.view.activity.MainActivity

object Injector {

    private lateinit var appComponent: IAppComponent
    private lateinit var activityComponent: ActivityComponent

    fun initAppComponent(application: Application) {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun initAndInjectActivityComponent(activity: MainActivity) {
        activityComponent = appComponent.plus(ActivityModule(activity))
    }

    fun injectActionPowerReceiver(receiver: ActionPowerReceiver) = appComponent.inject(receiver)

    fun <VM : ViewModel> provideViewModel(activity: AppCompatActivity, type: Class<VM>): VM =
        ViewModelProvider(activity, activityComponent.getViewModelFactory())[type]

    fun <VM : ViewModel> provideViewModel(fragment: Fragment, type: Class<VM>): VM =
        ViewModelProvider(fragment, activityComponent.getViewModelFactory())[type]
}