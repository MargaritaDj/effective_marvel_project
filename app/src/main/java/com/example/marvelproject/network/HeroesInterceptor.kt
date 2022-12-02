package com.example.marvelproject.network

import okhttp3.Interceptor
import okhttp3.Response

class HeroesInterceptor( private val ts: String,
                         private val apikey: String,
                         private val hash: String
                         ): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

       val requestUrl = chain.request().url.newBuilder()
           .addQueryParameter("ts", ts)
           .addQueryParameter("apikey", apikey)
           .addQueryParameter("hash", hash)
           .build()

       val request = chain.request().newBuilder()
           .url(requestUrl)
           .build()

        return chain.proceed(request)
    }
}
