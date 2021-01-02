package com.shaadicom.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.shaadicom.R
import com.shaadicom.database.User
import com.shaadicom.database.UserViewModel
import com.shaadicom.utils.BaseActivity
import com.shaadicom.utils.ResourceStatus
import com.shaadicom.utils.StatusType

class MainActivity :BaseActivity() {

    var gson = Gson()
    private lateinit var ViewModel: UserMatchViewModel
    private lateinit var ViewModel2: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModel = ViewModelProviders.of(this)[UserMatchViewModel::class.java]
        ViewModel2 = ViewModelProviders.of(this)[UserViewModel::class.java]
        setUpListeners()
        setupObservers()
    }

    private fun setUpListeners() {
        //calling the api
        ViewModel.callMatchData(this)
    }
    @SuppressLint("WrongConstant")
    private fun setupObservers() {
        //observing the response received from the api
        ViewModel.MatchDataResponseLiveWeatherData.observe(this, Observer {
            //if not empty data initalizing the recyl view and setting the weather details
            if (it.results.size != 0) {
               for (i in 0 until it.results.size){
                   val user_string = gson.toJson(it.results[i])
                   val user = gson.fromJson(user_string,User::class.java)
                   ViewModel2.addUser(user)
               }

                val recyclerView = findViewById<RecyclerView>(R.id.weather_recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                val adapter = UsersRecyclerView(ViewModel2)
                recyclerView.adapter = adapter
                ViewModel2.readAllData.observe(this, Observer { user ->
                    adapter.setData(user)
                })

            } else {
                Toast.makeText(this, "Not Successful", Toast.LENGTH_SHORT).show()
            }
        })
        //observe the status code of the api hit, whether it is a success or fail
        ViewModel.MatchDataApiCallStatus.observe(this, Observer {
            processStatus(it)
        })
    }
    private fun processStatus(resource: ResourceStatus) {

        when (resource.status) {
            StatusType.SUCCESS -> {
                dismissDialog()
            }
            StatusType.EMPTY_RESPONSE -> {
                dismissDialog()
            }
            StatusType.PROGRESSING -> {
                showDialog()
            }

            StatusType.ERROR -> {
                Toast.makeText(this, "Please try again. Server error", Toast.LENGTH_LONG).show()
                dismissDialog()
            }
            StatusType.LOADING_MORE -> {
                // CommonUtils().showSnackbar(binding.root, "Loading more..")
            }
            StatusType.NO_NETWORK -> {
                Toast.makeText(this, "Please check internet connection", Toast.LENGTH_LONG).show()
            }
            StatusType.SESSION_EXPIRED -> {
                Toast.makeText(this, "session expired", Toast.LENGTH_LONG).show()
            }
        }
    }
}