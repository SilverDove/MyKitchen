package com.example.mykitchen.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyKitchenApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@MyKitchenApplication)
            // modules
            modules(presentationModule, domainModule, dataModule)
        }
    }
}