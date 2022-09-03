package com.uniton.obab.network.room

import android.util.Log
import com.uniton.obab.model.Repository
import com.uniton.obab.model.RoomRepository
import com.uniton.obab.model.RoomRequest
import com.uniton.obab.network.Api
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoomService(val roomCallback: RoomsCallback) {
    companion object {
        const val BASE_URL = "http://3.37.236.75:8080"
    }

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun postRooms(roomRequest: RoomRequest) {
        val api = retrofit.create(Api::class.java)
        api.postRooms(roomRequest).enqueue(object : Callback<RoomRepository> {
            override fun onResponse(
                call: Call<RoomRepository>,
                response: Response<RoomRepository>
            ) {
                response.body()?.let {
                    if (it.responseCode == "CM00") {
                        roomCallback.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<RoomRepository>, t: Throwable) {
                Log.w("Network", t)
            }

        }
        )
    }


}
