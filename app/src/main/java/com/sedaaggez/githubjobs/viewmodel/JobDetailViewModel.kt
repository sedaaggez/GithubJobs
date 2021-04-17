package com.sedaaggez.githubjobs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.githubjobs.model.Job
import com.sedaaggez.githubjobs.service.JobDatabase
import kotlinx.coroutines.launch

class JobDetailViewModel(application: Application) : BaseViewModel(application)  {
    val jobLiveData = MutableLiveData<Job>()

    fun getDataFromRoom(uuid: Int) {
        launch {
            val dao = JobDatabase(getApplication()).jobDao()
            val job = dao.getJob(uuid)
            jobLiveData.value = job
        }
    }
}