package com.raahi.youtubeapi.di.modules

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.raahi.youtubeapi.BuildConfig
import com.raahi.youtubeapi.YouTubeAPIApplication
import com.raahi.youtubeapi.data.DataManager
import com.raahi.youtubeapi.data.YouTubeAPIDataManager
import com.raahi.youtubeapi.data.network.ApiHelper
import com.raahi.youtubeapi.di.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: YouTubeAPIApplication) {
    @Provides
    @ApplicationContext
    fun providesContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @get:Singleton
    @get:Provides
    val oKHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .build()
                )
            }
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun getRetrofit(defaultHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(defaultHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getApiHelper(apiHelper: YouTubeAPIDataManager): ApiHelper {
        return apiHelper
    }

    @Provides
    @Singleton
    fun getDataManager(dataManager: YouTubeAPIDataManager): DataManager {
        return dataManager
    }

}