package com.sedaaggez.githubjobs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.githubjobs.R
import com.sedaaggez.githubjobs.model.Job
import kotlinx.android.synthetic.main.item_job.view.*

class JobAdapter(val jobList: ArrayList<Job>) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_job, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.view.textViewTitle.text = jobList[position].title
        holder.view.textViewCompany.text = jobList[position].company
        holder.view.textViewLocation.text = jobList[position].location
        holder.view.textViewType.text = jobList[position].type
        // TODO: set imageViewCompanyLogo
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    fun updateJobList(newJobList: List<Job>) {
        jobList.clear()
        jobList.addAll(newJobList)
        notifyDataSetChanged()
    }
}