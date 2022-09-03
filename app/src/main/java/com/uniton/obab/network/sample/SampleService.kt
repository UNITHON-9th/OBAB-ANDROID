package com.uniton.obab.network.sample

import com.uniton.obab.model.Repository
import com.uniton.obab.network.Api
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SampleService(val networkResult:SampleCallback) {
    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getRepositories(owner: String, repo: String) {
        val api = retrofit.create(Api::class.java)
        api.getRepositories(owner, repo).enqueue(
            object : Callback<Repository> {
                override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                    networkResult.onSuccess()
                }

                override fun onFailure(call: Call<Repository>, t: Throwable) {
                    networkResult.onFail()
                }

            }
        )
    }


}
