package com.sedaaggez.githubjobs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedaaggez.githubjobs.R
import com.sedaaggez.githubjobs.adapter.JobAdapter
import com.sedaaggez.githubjobs.viewmodel.JobViewModel
import kotlinx.android.synthetic.main.fragment_jobs.*
import kotlinx.android.synthetic.main.item_job.*

class JobsFragment : Fragment() {

    private lateinit var viewModel : JobViewModel
    private val jobAdapter = JobAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(JobViewModel::class.java)
        viewModel.getData()

        recyclerViewJobs.layoutManager = LinearLayoutManager(context)
        recyclerViewJobs.adapter = jobAdapter

        observeLiveData()


        return inflater.inflate(R.layout.fragment_jobs, container, false)
    }

    fun observeLiveData() {
        viewModel.jobs.observe(viewLifecycleOwner, Observer {jobs ->
            jobs?.let {
                recyclerViewJobs.visibility = View.VISIBLE
                jobAdapter.updateJobList(jobs)
            }
        })

        viewModel.jobError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if(it) {
                    textViewError.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    recyclerViewJobs.visibility = View.GONE
                } else {
                    textViewError.visibility = View.GONE

                }
            }
        })

        viewModel.jobLoading.observe(viewLifecycleOwner, Observer {loading ->
            loading?.let {
                if(it) {
                    progressBar.visibility = View.VISIBLE
                    textViewError.visibility = View.GONE
                    recyclerViewJobs.visibility = View.GONE

                } else {
                    progressBar.visibility = View.GONE

                }
            }
        })
    }

}