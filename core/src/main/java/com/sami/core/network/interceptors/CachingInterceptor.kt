package com.sami.core.network.interceptors

import android.content.Context
import com.sami.core.utils.hasNetwork
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CachingInterceptor @Inject constructor(@ApplicationContext private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = if (context.hasNetwork() == true)
            request.newBuilder().header("Cache-Control", "public, max-age=$MAX_AGE_SECONDS").build()
        else
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$MAX_STALE_SECONDS")
                .build()

        return chain.proceed(request)
    }

    companion object {
        const val CACHE_SIZE_BYTES = 5 * 1024 * 1024
        private const val MAX_AGE_SECONDS = 5
        private const val MAX_STALE_SECONDS = 60 * 60 * 24 * 7
    }
}
