package com.raahi.youtubeapi.di.components

import com.raahi.youtubeapi.di.PresenterScope
import com.raahi.youtubeapi.di.modules.PresenterModule
import com.raahi.youtubeapi.ui.details.DetailsContract
import com.raahi.youtubeapi.ui.details.DetailsPresenter
import dagger.Component

@PresenterScope
@Component(dependencies = [ApplicationComponent::class], modules = [PresenterModule::class])
interface PresenterComponent {

    fun inject(detailsPresenter: DetailsPresenter)

}