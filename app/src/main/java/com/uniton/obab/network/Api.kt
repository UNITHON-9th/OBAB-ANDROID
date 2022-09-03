package com.uniton.obab.network

import com.uniton.obab.model.Repository
import com.uniton.obab.model.RoomRepository
import com.uniton.obab.model.RoomRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("/repos/{owner}/{repo}")
    fun getRepositories(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Call<Repository>

    @POST("/rooms")
    fun postRooms(@Body params: RoomRequest): Call<RoomRepository>
}
