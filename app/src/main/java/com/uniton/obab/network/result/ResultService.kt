package com.uniton.obab.network.result

import android.util.Log
import com.uniton.obab.model.response.ResultResponse
import com.uniton.obab.network.Api
import com.uniton.obab.network.room.enter.EnterRoomService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultService(val resultCallback: ResultCallback) {
    private val headerInterceptor = Interceptor {
        val request = it.request()
            .newBuilder()
            .build()
        return@Interceptor it.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(EnterRoomService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getResults(roomNo: String) {
        Log.w("TAG", "sendUserChoice")
        val api = retrofit.create(Api::class.java)
        api.getResult(roomNo).enqueue(object : Callback<ResultResponse> {
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>,
            ) {
                response.body()?.let { resultResponse ->
                    Log.w("TAG", resultResponse.toString())
                    resultCallback.onSuccess(resultResponse)
                } ?: kotlin.run {
                    resultCallback.onFailure("please try again")
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                resultCallback.onFailure(t.message.toString())
            }

        })
    }
}
