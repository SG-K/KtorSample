package com.example.ktorsample.di

import com.example.ktorsample.network.GitHubRepo
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.Json
import org.koin.android.BuildConfig
import org.koin.dsl.module
import java.util.concurrent.TimeUnit



val networkModule = module {
    single { okHttpKtor }
    single { GitHubRepo(get()) }
}


//region Ktor

private val okHttpKtor = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json.nonstrict)
    }
}

//endregion