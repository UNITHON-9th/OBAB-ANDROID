package com.uniton.obab.network.room.enter

import android.util.Log
import com.uniton.obab.model.EnterRoomRepository
import com.uniton.obab.model.EnterRoomRequest
import com.uniton.obab.network.Api
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EnterRoomService(val enterRoomCallback: EnterRoomCallback) {
    companion object {
        const val BASE_URL = "http://43.200.197.100:8080"
    }

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun postEnterRoom(enterRequest: EnterRoomRequest) {
        val api = retrofit.create(Api::class.java)
        api.postEnterRooms(enterRequest).enqueue(object : Callback<EnterRoomRepository> {
            override fun onResponse(
                call: Call<EnterRoomRepository>,
                response: Response<EnterRoomRepository>
            ) {
                response.body()?.let {
                    if (it.responseCode == "CM00") {
                        enterRoomCallback.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<EnterRoomRepository>, t: Throwable) {
                Log.w("Network", t)
            }
        })
    }
}
