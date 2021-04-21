package com.sedaaggez.githubjobs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.githubjobs.R
import com.sedaaggez.githubjobs.databinding.ItemJobBinding
import com.sedaaggez.githubjobs.model.Job

class JobAdapter(val jobList: ArrayList<Job>) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(var view: ItemJobBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemJobBinding>(inflater, R.layout.item_job, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.view.job = jobList[position]
        // TODO: Listener bindings
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