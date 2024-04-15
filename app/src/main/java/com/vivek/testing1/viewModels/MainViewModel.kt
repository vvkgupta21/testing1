package com.vivek.testing1.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vivek.testing1.models.UserDataResponseModelItem
import com.vivek.testing1.netwrok.StateLiveData
import com.vivek.testing1.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val TAG = "JsonTest"
class MainViewModel(private val repository: MainRepository): ViewModel() {

    lateinit var viewModelJob : Job

    fun getUserDetails(): StateLiveData<ArrayList<UserDataResponseModelItem>>{
        val data = StateLiveData<ArrayList<UserDataResponseModelItem>>()
        data.postLoading()
        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
            try {
                val response = repository.getUserDetails()
                response.value?.let {
                    data.postSuccess(it)
                }
            } catch (e: Exception){
                Log.d(TAG, "getUserDetails: ${e.message}")
            }
        }
        return data
    }

}