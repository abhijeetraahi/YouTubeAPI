package com.raahi.youtubeapi.di.components

import android.app.Application
import android.content.Context
import com.raahi.youtubeapi.YouTubeAPIApplication
import com.raahi.youtubeapi.data.DataManager
import com.raahi.youtubeapi.di.ApplicationContext
import com.raahi.youtubeapi.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: YouTubeAPIApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager
}
