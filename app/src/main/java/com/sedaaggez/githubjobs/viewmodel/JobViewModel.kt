package com.sedaaggez.githubjobs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedaaggez.githubjobs.model.Job

class JobViewModel : ViewModel() {
    val jobs = MutableLiveData<List<Job>>()
    val jobError = MutableLiveData<Boolean>()
    val jobLoading = MutableLiveData<Boolean>()

    fun getData() {
        // TODO: get data
    }


}