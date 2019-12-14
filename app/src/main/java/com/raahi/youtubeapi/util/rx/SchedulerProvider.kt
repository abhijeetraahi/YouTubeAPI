package com.raahi.youtubeapi.util.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun ui(): Scheduler
    fun io(): Scheduler
}