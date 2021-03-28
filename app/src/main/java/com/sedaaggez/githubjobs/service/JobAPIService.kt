package com.sedaaggez.githubjobs.service

import com.sedaaggez.githubjobs.model.Job
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class JobAPIService {

    private val BASE_URL = "https://jobs.github.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(JobAPI::class.java)

    fun getData(description: String) : Single<List<Job>> {
        return api.getDescriptionJobs(description)
    }

}