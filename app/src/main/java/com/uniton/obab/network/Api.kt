package com.uniton.obab.network

import com.uniton.obab.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/repos/{owner}/{repo}")
    fun getRepositories(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Call<Repository>

    @POST("/rooms")
    fun postRooms(@Body params: CreateRoomRequest): Call<CreateRoomRepository>
    @POST("/rooms/enter")
    fun postEnterRooms(@Body params: EnterRoomRequest): Call<EnterRoomRepository>
    @GET("surveys/{deviceId}")
    fun getPersonalResult(@Path ("deviceId") deviceId: String, @Query("roomNo") roomNo: String): Call<PersonalResultRepository>
}
