package com.scccrt.rekto.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.scccrt.rekto.data.remote.RektoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val CONTENT_TYPE = "application/json"

    @Provides
    @Singleton
    fun provideRektoApi(): RektoApi = retrofit().create()

    @Provides
    @Singleton
    fun provideDefaultCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    private fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(converterFactory())
            .client(okHttpClient())
            .build()

    private fun converterFactory(): Converter.Factory {
        val jsonSerializer = Json { ignoreUnknownKeys = true }
        val contentType = CONTENT_TYPE.toMediaType()

        return jsonSerializer.asConverterFactory(contentType)
    }

    private fun okHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .build()

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}