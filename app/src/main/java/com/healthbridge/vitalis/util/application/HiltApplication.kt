package com.healthbridge.vitalis.util.application

import android.app.Application
import com.healthbridge.vitalis.feature_records.data.AppContainer
import com.healthbridge.vitalis.feature_records.data.AppContainerImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication: Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(this)
    }
}