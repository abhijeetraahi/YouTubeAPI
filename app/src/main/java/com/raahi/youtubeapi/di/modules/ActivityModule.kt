package com.raahi.youtubeapi.di.modules

import androidx.appcompat.app.AppCompatActivity
import com.raahi.youtubeapi.data.network.ApiHelper
import com.raahi.youtubeapi.data.network.YouTubeApiHelper
import com.raahi.youtubeapi.di.ActivityScope
import com.raahi.youtubeapi.util.rx.SchedulerProvider
import com.raahi.youtubeapi.util.rx.YouTubeAPISchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }

    @Provides
    fun getSchedulerProvider(schedulerProvider: YouTubeAPISchedulerProvider): SchedulerProvider {
        return schedulerProvider
    }

    @Provides
    fun getCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun getApiHelper(apiHelper: YouTubeApiHelper): ApiHelper {
        return apiHelper
    }
}
