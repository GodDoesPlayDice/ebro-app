package com.example.ebroapp.di.component

import com.example.ebroapp.di.module.ActivityModule
import com.example.ebroapp.receiver.ActionPowerReceiver

interface IAppComponent {

    fun inject(receiver: ActionPowerReceiver)

    fun plus(activityModule: ActivityModule): ActivityComponent
}
