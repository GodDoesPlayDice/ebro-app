package com.example.ebroapp.di.module

import androidx.appcompat.app.AppCompatActivity
import com.example.ebroapp.di.module.vm.ViewModelModule
import com.example.ebroapp.view.activity.MainActivity
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class ActivityModule(private val activity: MainActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity
}
