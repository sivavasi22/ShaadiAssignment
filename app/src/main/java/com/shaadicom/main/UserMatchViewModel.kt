package com.shaadicom.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaadicom.repository.ShaadiRepository
import com.shaadicom.data.ShaadiData
import com.shaadicom.utils.ResourceStatus

class UserMatchViewModel: ViewModel() {

    // livedata for observing api call status and getting response respectively
    var MatchDataApiCallStatus = MutableLiveData<ResourceStatus>()
    var MatchDataResponseLiveWeatherData = MutableLiveData<ShaadiData>()

    fun callMatchData(context: Context) {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo: NetworkInfo? = connectivityManager.getActiveNetworkInfo()
        val connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected

        if (connected) { //check user has an active internet
            MatchDataApiCallStatus.value = ResourceStatus.loading()//change the value of api call status to check in main activity
            ShaadiRepository.matchSummaryRepoResult() { isSuccess, response ->

                if (isSuccess) {
                    MatchDataApiCallStatus.value =
                        ResourceStatus.success("")//change the value of api call status to check in main activity
                    MatchDataResponseLiveWeatherData.postValue(response) //store the response in response live data
                } else {
                    if (response?.results?.size != 0) { // check if the response message is successful one
                        MatchDataApiCallStatus.value =
                            ResourceStatus.error("") //change the value of api call status to check in main activity
                    } else {
                        MatchDataApiCallStatus.value =
                            ResourceStatus.error("") //change the value of api call status to check in main activity
                    }

                }

            }
        }
        else {
            Log.e("CallStatus","No netwrok")
            MatchDataApiCallStatus.value = ResourceStatus.nonetwork()
        }

    }
}