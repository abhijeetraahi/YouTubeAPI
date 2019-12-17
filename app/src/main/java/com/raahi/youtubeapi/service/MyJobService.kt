package com.raahi.youtubeapi.service

import android.annotation.TargetApi
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import androidx.localbroadcastmanager.content.LocalBroadcastManager


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService: JobService() {
    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        val intent = Intent("com.raahi.youtubeapi")
        intent.putExtra("value", "test")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        sendBroadcast(intent)
        return true
    }
}