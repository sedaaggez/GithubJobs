package com.sedaaggez.githubjobs.service

import com.sedaaggez.githubjobs.model.Job
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface JobAPI {

    @GET("positions.json")
    fun getDescriptionJobs(@Query("description") description: String): Single<List<Job>>

}