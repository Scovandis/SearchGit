package com.example.searchviewgit.network

import com.example.searchviewgit.cons.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory




object ApiService {
    private var ourInstace : Retrofit? = null

    val instance: Retrofit
    get() {
        if (ourInstace == null){
            ourInstace = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return ourInstace!!
    }
}


