package com.vivek.testing1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vivek.testing1.MainViewModelFactory
import com.vivek.testing1.R
import com.vivek.testing1.adapter.UserListAdapter
import com.vivek.testing1.databinding.ActivityMainBinding
import com.vivek.testing1.netwrok.JsonApi
import com.vivek.testing1.netwrok.StateData
import com.vivek.testing1.netwrok.WebService
import com.vivek.testing1.repository.MainRepository
import com.vivek.testing1.viewModels.MainViewModel

const val TAG = "JsonApi"
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = MainRepository(JsonApi)
        viewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        initAdapter()

        viewModel.getUserDetails().observe(this) {
            it?.let { stateData ->
                when (stateData.status) {
                    StateData.DataStatus.ERROR -> {
                        Log.d(TAG, "onCreate: ${stateData.error?.message}")

                    }

                    StateData.DataStatus.LOADING -> {}
                    StateData.DataStatus.SUCCESS -> {
                        Log.d(TAG, "onCreate: ${stateData.data}")
                        stateData.data?.let { it1 -> adapter.initList(it1) }
                    }

                    else -> {}
                }
            }
        }


    }

    private fun initAdapter(){
        adapter = UserListAdapter(arrayListOf())
        binding.rv.adapter = adapter
    }
}