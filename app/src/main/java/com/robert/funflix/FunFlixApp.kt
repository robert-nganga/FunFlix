package com.robert.funflix

import android.app.Application
import com.robert.funflix.di.appModule
import com.robert.funflix.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
class FunFlixApp :
    Application(),
    KoinStartup {
    @KoinExperimentalAPI
    override fun onKoinStartup(): KoinConfiguration = KoinConfiguration {
        androidLogger()
        androidContext(this@FunFlixApp)
        modules(appModule, viewModelModule)
    }
}
