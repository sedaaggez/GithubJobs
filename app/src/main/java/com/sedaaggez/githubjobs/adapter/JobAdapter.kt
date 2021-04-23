package com.sedaaggez.githubjobs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.githubjobs.R
import com.sedaaggez.githubjobs.databinding.ItemJobBinding
import com.sedaaggez.githubjobs.model.Job
import com.sedaaggez.githubjobs.view.JobsFragmentDirections
import kotlinx.android.synthetic.main.item_job.view.*

class JobAdapter(val jobList: ArrayList<Job>) : RecyclerView.Adapter<JobAdapter.JobViewHolder>(), JobClickListener {

    class JobViewHolder(var view: ItemJobBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemJobBinding>(inflater, R.layout.item_job, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.view.job = jobList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    fun updateJobList(newJobList: List<Job>) {
        jobList.clear()
        jobList.addAll(newJobList)
        notifyDataSetChanged()
    }

    override fun onJobClicked(v: View) {
            val uuid = v.jobUuidText.text.toString().toInt()
            val action = JobsFragmentDirections.actionJobsFragmentToJobDetailFragment(uuid)
            Navigation.findNavController(v).navigate(action)
    }
}