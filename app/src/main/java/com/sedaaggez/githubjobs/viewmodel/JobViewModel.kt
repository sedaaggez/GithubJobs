package com.sedaaggez.githubjobs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.githubjobs.model.Job
import com.sedaaggez.githubjobs.service.JobAPIService
import com.sedaaggez.githubjobs.service.JobDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class JobViewModel(application: Application) : BaseViewModel(application) {
    val jobs = MutableLiveData<List<Job>>()
    val jobError = MutableLiveData<Boolean>()
    val jobLoading = MutableLiveData<Boolean>()

    private val jobAPIService = JobAPIService()
    private val disposable = CompositeDisposable()

    fun getData(description: String) {
        jobError.value = false
        disposable.add(
            jobAPIService.getData(description)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Job>>() {
                    override fun onSuccess(t: List<Job>) {
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        jobError.value = true
                        jobLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showJobs(jobList: List<Job>) {
        jobs.value = jobList
        jobError.value = false
        jobLoading.value = false
    }

    private fun storeInSQLite(list: List<Job>) {
        launch {
            val dao = JobDatabase(getApplication()).jobDao()
            dao.deleteAllJobs()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showJobs(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}