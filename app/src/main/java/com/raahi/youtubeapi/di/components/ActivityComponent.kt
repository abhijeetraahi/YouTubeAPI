package com.raahi.youtubeapi.di.components

import com.raahi.youtubeapi.di.ActivityScope
import com.raahi.youtubeapi.di.modules.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

}
