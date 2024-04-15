package com.vivek.testing1.netwrok

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vivek.testing1.models.UserDataResponseModelItem
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val baseUrl = "https://jsonplaceholder.typicode.com"

private val retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface WebService {
    @GET("/photos")
    fun getUserDetailsAsync(): Deferred<ArrayList<UserDataResponseModelItem>>
}

object JsonApi {
    val webService: WebService by lazy {
        retrofit.create(WebService::class.java)
    }
}