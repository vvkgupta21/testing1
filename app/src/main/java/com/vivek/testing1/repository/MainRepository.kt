package com.vivek.testing1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vivek.testing1.models.UserDataResponseModelItem
import com.vivek.testing1.netwrok.JsonApi
import com.vivek.testing1.netwrok.WebService

class MainRepository(private val service: JsonApi) {
   suspend fun getUserDetails(): LiveData<ArrayList<UserDataResponseModelItem>> {
        val data = MutableLiveData<ArrayList<UserDataResponseModelItem>>()
        val response = service.webService.getUserDetailsAsync().await()
        data.value = response
        return data
    }
}