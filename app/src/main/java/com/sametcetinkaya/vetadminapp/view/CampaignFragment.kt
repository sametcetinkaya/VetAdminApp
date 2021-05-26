package com.sametcetinkaya.vetadminapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sametcetinkaya.vetadminapp.R
import com.sametcetinkaya.vetadminapp.adapter.CampaignAdapter
import com.sametcetinkaya.vetadminapp.viewModel.CampaignViewModel
import kotlinx.android.synthetic.main.fragment_campaign.*


class CampaignFragment : Fragment() {

    private lateinit var campaignListViewModel: CampaignViewModel
    private val recyclerAdapter = CampaignAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaignListViewModel = ViewModelProviders.of(this).get(CampaignViewModel::class.java)
        campaignListViewModel.getCampaignList()
        setupRecycler()
        observerLiveData()
        addCampaign.setOnClickListener {
                campaignAction(it)
        }


    }

    private fun observerLiveData() {
        campaignListViewModel.campaignList.observe(viewLifecycleOwner, Observer { campaignAdd ->
            campaignAdd?.let {
                campaignRecyclerView.visibility = View.VISIBLE
                recyclerAdapter.campaignListUpdate(campaignAdd)
            }
        })
    }

    private fun setupRecycler() {
        campaignRecyclerView.layoutManager = LinearLayoutManager(context)
        campaignRecyclerView.adapter = recyclerAdapter

    }

    private fun campaignAction(view: View) {
        val action = CampaignFragmentDirections.actionCampaignFragmentToAddCampaignFragment()
        Navigation.findNavController(view).navigate(action)

    }

}