package com.example.ktorsample

import android.app.Application
import com.example.ktorsample.di.networkModule
import com.example.ktorsample.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KtorSampleApplicationClass : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KtorSampleApplicationClass)
            androidLogger()
            modules(listOf(viewmodelModule, networkModule))
        }
    }

}