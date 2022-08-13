package com.example.ebroapp.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.ebroapp.di.module.ActivityModule
import com.example.ebroapp.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun getViewModelFactory(): ViewModelProvider.Factory
}
