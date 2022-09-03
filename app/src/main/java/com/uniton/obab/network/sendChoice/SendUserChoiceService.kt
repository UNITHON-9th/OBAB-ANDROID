package com.uniton.obab.network.sendChoice

import com.uniton.obab.model.request.UserChoiceRequest
import com.uniton.obab.model.response.UserChoiceResponse
import com.uniton.obab.network.Api
import com.uniton.obab.network.room.enter.EnterRoomService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SendUserChoiceService(val sendUserChoiceCallback: SendUserChoiceCallback) {
    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(EnterRoomService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun sendUserChoice(deviceId: String, sendUserChoiceRequest: UserChoiceRequest) {
        val api = retrofit.create(Api::class.java)
        api.postUserChoice(deviceId, sendUserChoiceRequest).enqueue(object : Callback<UserChoiceResponse> {
            override fun onResponse(
                call: Call<UserChoiceResponse>,
                response: Response<UserChoiceResponse>,
            ) {
                response.body()?.let {
                    if (it.responseCode == "CM99") {
                        sendUserChoiceCallback.onSuccess()
                    }
                }
            }

            override fun onFailure(call: Call<UserChoiceResponse>, t: Throwable) {
                sendUserChoiceCallback.onFail(t.message)
            }

        })
    }
}
