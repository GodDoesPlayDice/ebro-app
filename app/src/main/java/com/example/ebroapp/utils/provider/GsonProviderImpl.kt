package com.example.ebroapp.utils.provider

import android.net.Uri
import com.example.ebroapp.utils.music.UriAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject

class GsonProviderImpl @Inject constructor() : GsonProvider {

    override fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(Uri::class.java, UriAdapter())
        builder.setPrettyPrinting()
        return builder.create()
    }
}