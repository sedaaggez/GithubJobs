package com.sedaaggez.githubjobs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.sedaaggez.githubjobs.R
import kotlinx.android.synthetic.main.fragment_search_job.*

class SearchJobFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        buttonSubmit.setOnClickListener(View.OnClickListener {
            if (editTextSearchJob.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please do not leave blank!", Toast.LENGTH_SHORT).show()
            } else {
                val description = editTextSearchJob.text.toString()
                val action = SearchJobFragmentDirections.actionSearchJobFragmentToJobsFragment(description)
                Navigation.findNavController(it).navigate(action)
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_job, container, false)
    }

}