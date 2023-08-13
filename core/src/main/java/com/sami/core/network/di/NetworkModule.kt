package com.sami.core.network.di

import android.content.Context
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.sami.core.BuildConfig
import com.sami.core.network.interceptors.ApiKeyInterceptor
import com.sami.core.network.interceptors.CachingInterceptor
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        cachingInterceptor: CachingInterceptor
    ): OkHttpClient {
        val cacheSize = CachingInterceptor.CACHE_SIZE_BYTES.toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(OkHttpProfilerInterceptor())
            .addInterceptor(cachingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(factory: Converter.Factory, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NY_TIMES_BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Wrapped.ADAPTER_FACTORY)
            .build()
    }
}