package com.robert.funflix.di

import com.robert.funflix.core.data.HttpClientFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

val appModule =
    module {
        single { HttpClientFactory.createClient(engine = OkHttp.create(), context = get()) }
    }
