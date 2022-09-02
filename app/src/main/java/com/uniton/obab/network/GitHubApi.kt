package com.uniton.obab.network

import com.uniton.obab.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("/repos/{owner}/{repo}")
    fun getRepositories(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Call<Repository>
}
