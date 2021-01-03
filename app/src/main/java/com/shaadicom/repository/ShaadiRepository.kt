package com.shaadicom.repository

import com.shaadicom.remote.ShaadiApiService
import com.shaadicom.data.ShaadiData
import com.shaadicom.database.User
import retrofit2.Call
import retrofit2.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

object ShaadiRepository {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun matchSummaryRepoResult(onResult: (isSuccess: Boolean, response: ShaadiData?) -> Unit){
        scope.launch {
            //call the interface to build the http call and execute
            ShaadiApiService.instance.matchSummary().enqueue(object : retrofit2.Callback<ShaadiData> {
                override fun onFailure(call: Call<ShaadiData>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<ShaadiData>,
                    response: Response<ShaadiData>
                ) {
                    if (response != null && response.isSuccessful)
                        onResult(true, response.body()!!)//on success
                    else
                        onResult(false, null)//on failure
                }
            })
        }
    }

    /*fun matchSummaryRepoResultOffline(onResult: (isSuccess: Boolean, response: List<User>?) -> Unit){
        scope.launch {
            //call the interface to build the http call and execute
            ShaadiApiService.instance.matchSummary().enqueue(object : retrofit2.Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    if (response != null && response.isSuccessful)
                        onResult(true, response.body()!!)//on success
                    else
                        onResult(false, null)//on failure
                }
            })
        }
    }*/
}