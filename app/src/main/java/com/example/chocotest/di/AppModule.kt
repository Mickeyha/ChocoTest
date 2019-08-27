package com.example.chocotest.di

import com.example.chocotest.service.ChocoService
import com.example.chocotest.service.ChocoService.Companion.CHOCO_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val userInfoViewModelModule = module {

}

val repositoryModule = module {

}

val networkModule = module {
    single { generateOkHttpClient() }
    single { generateRetrofit(get()) }
    single { generateChocoService(get()) }
}

fun generateOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

fun generateRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(CHOCO_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun generateChocoService(retrofit: Retrofit): ChocoService =
    retrofit.create(ChocoService::class.java)

val appModule = listOf(
    userInfoViewModelModule,
    repositoryModule,
    networkModule
)