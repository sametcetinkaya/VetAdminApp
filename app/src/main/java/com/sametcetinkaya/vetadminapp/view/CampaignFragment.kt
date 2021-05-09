package com.sametcetinkaya.vetadminapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sametcetinkaya.vetadminapp.R
import com.sametcetinkaya.vetadminapp.adapter.RecyclerAdapter
import com.sametcetinkaya.vetadminapp.viewModel.CampaignAddViewModel
import kotlinx.android.synthetic.main.fragment_campaign.*


class CampaignFragment : Fragment() {
    private val recyclerAdapter = RecyclerAdapter(arrayListOf())
    private lateinit var campaignAddViewModel: CampaignAddViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaignAddViewModel = ViewModelProviders.of(this).get(CampaignAddViewModel::class.java)
        campaignAddViewModel.campaignAdd()
        observerLiveData()
        setupRecycler()

    }

    private fun observerLiveData() {
        campaignAddViewModel.campaignAdd.observe(viewLifecycleOwner, Observer {

        })

    }

    private fun setupRecycler() {
        campaignRecyclerView.layoutManager= LinearLayoutManager(context)
        campaignRecyclerView.adapter=recyclerAdapter
        recyclerAdapter.notifyDataSetChanged()

    }
}