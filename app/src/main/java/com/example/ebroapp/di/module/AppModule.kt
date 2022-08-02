package com.example.ebroapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.ebroapp.di.module.data.RepositoryModule
import com.example.ebroapp.domain.repository.DomainRepository
import com.example.ebroapp.remote.repository.RemoteService
import com.example.ebroapp.utils.*
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        RepositoryModule::class,
        AppModule.Bindings::class
    ]
)
internal open class AppModule(private val application: Application) {

    @Provides
    internal fun provideContext(): Context = application

    @Provides
    internal fun provideGson(gsonProvider: GsonProvider): Gson = gsonProvider.provideGson()

    @Provides
    internal fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    @Provides
    internal fun providePlayer(context: Context, domainRepository: DomainRepository): Player =
        Player(context, domainRepository).apply { setPlayList(context) }

    @Provides
    internal fun provideLocationListener(context: Context): LocationListener =
        LocationListener(context)

    @Provides
    internal fun provideRemoteService(): RemoteService = RetrofitService.getService()

    @Module
    interface Bindings {
        @Binds
        fun bindGsonProvider(impl: GsonProviderImpl): GsonProvider
    }

    companion object {
        private const val APP_PREFERENCES = "APP_PREFERENCES"
    }
}