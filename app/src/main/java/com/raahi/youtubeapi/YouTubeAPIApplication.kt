package com.raahi.youtubeapi

import android.app.Application
import com.raahi.youtubeapi.di.components.ApplicationComponent
import com.raahi.youtubeapi.di.components.DaggerApplicationComponent
import com.raahi.youtubeapi.di.modules.ApplicationModule

class YouTubeAPIApplication: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}