package com.sedaaggez.githubjobs.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedaaggez.githubjobs.R
import com.sedaaggez.githubjobs.util.downloadFromUrl
import com.sedaaggez.githubjobs.util.placeholderProgressBar
import com.sedaaggez.githubjobs.viewmodel.JobDetailViewModel
import kotlinx.android.synthetic.main.fragment_job_detail.*

class JobDetailFragment : Fragment() {
    private lateinit var viewModel : JobDetailViewModel
    private var jobUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_job_detail, container, false)
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
                textViewDetailTitle.text = job.title
                textViewDetailLocation.text = job.location
                textViewDetailType.text = job.type
                textViewDetailTitle.text = job.title
                textViewDetailDescription.text = fromHtml(job.description.toString())
                context?.let {
                    imageViewDetailCompanyLogo.downloadFromUrl(job.companyLogo, placeholderProgressBar(it))
                }
            }
        })
    }


    @Suppress("DEPRECATION")
    private fun fromHtml(htmlBody: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(htmlBody, Html.FROM_HTML_MODE_LEGACY).toString()
        else
            Html.fromHtml(htmlBody).toString()
    }

}