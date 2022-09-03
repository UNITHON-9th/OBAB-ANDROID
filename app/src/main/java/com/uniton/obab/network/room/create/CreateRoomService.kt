package com.uniton.obab.network.room.create

import android.util.Log
import com.uniton.obab.model.CreateRoomRepository
import com.uniton.obab.model.CreateRoomRequest
import com.uniton.obab.network.Api
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateRoomService(val roomCallback: CreateRoomCallback) {
    companion object {
        const val BASE_URL = "http://3.37.236.75:8080"
    }

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun postRooms(createRoomRequest: CreateRoomRequest) {
        val api = retrofit.create(Api::class.java)
        api.postRooms(createRoomRequest).enqueue(object : Callback<CreateRoomRepository> {
            override fun onResponse(
                call: Call<CreateRoomRepository>,
                response: Response<CreateRoomRepository>
            ) {
                response.body()?.let {
                    if (it.responseCode == "CM00") {
                        roomCallback.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<CreateRoomRepository>, t: Throwable) {
                Log.w("Network", t)
            }
        })
    }
}
