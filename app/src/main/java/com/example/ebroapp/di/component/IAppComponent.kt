package com.example.ebroapp.di.component

import com.example.ebroapp.di.module.ActivityModule

interface IAppComponent {
    fun plus(activityModule: ActivityModule): ActivityComponent
}
