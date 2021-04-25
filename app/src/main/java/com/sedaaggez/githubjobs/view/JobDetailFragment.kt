package com.sedaaggez.githubjobs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sedaaggez.githubjobs.R
import com.sedaaggez.githubjobs.databinding.FragmentJobDetailBinding
import com.sedaaggez.githubjobs.viewmodel.JobDetailViewModel

class JobDetailFragment : Fragment() {
    private lateinit var viewModel : JobDetailViewModel
    private var jobUuid = 0
    private lateinit var dataBinding: FragmentJobDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_job_detail,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(JobDetailViewModel::class.java)

        arguments?.let {
            jobUuid = JobDetailFragmentArgs.fromBundle(it).jobUuid
        }

        viewModel.getDataFromRoom(jobUuid)

        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.jobLiveData.observe(viewLifecycleOwner, Observer { job ->
            job?.let {
                dataBinding.job = job
            }
        })
    }
}