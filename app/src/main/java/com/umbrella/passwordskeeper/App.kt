package com.umbrella.passwordskeeper

import android.app.Application
import com.umbrella.passwordskeeper.di.localStorageModule
import com.umbrella.passwordskeeper.di.repositoriesModule
import com.umbrella.passwordskeeper.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(repositoriesModule, localStorageModule, useCasesModule)
        }
    }
}