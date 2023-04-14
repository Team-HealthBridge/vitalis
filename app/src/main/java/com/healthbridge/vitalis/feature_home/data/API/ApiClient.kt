package com.healthbridge.vitalis.feature_home.data.API

import com.healthbridge.vitalis.util.Constants.BACKEND_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    var retrofit: Retrofit? = null

    fun getApi(): ApiService {
        if(retrofit == null) {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .header("Authorization", "Basic cHJpbWFyeTpxTHhKVVZPS2VuV1R2Q0trWFFXbzF5NEpWcTNUR3A3VGN0bE45QWJwamVwME53azFiYlZLV25SWnNKMjZvV1Np")
                        .method(original.method, original.body)
                        .build()
                    chain.proceed(request)
                }
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BACKEND_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit?.create(ApiService::class.java) ?: throw Exception("Retrofit is null")
    }
}