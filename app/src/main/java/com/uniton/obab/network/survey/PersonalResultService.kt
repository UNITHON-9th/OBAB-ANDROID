package com.uniton.obab.network.survey

import android.util.Log
import com.uniton.obab.model.PersonalResultRepository
import com.uniton.obab.network.Api
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonalResultService(val personalResultCallback: PersonalResultCallback) {
    companion object {
        const val BASE_URL = "http://43.200.197.100:8080"
    }

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getPersonalResult(deviceId: String, roomNo: String) {
        val api = retrofit.create(Api::class.java)
        api.getPersonalResult(deviceId = deviceId, roomNo = roomNo)
            .enqueue(object : Callback<PersonalResultRepository> {
                override fun onResponse(
                    call: Call<PersonalResultRepository>,
                    response: Response<PersonalResultRepository>
                ) {
                    response.body()?.let {
                        if (it.responseCode == "CM00") {
                            personalResultCallback.onSuccess(it)
                        }
                    }
                }

                override fun onFailure(call: Call<PersonalResultRepository>, t: Throwable) {
                    Log.w("Network", t)
                }
            })
    }
}
