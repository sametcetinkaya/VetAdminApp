package com.sametcetinkaya.vetadminapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sametcetinkaya.vetadminapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kampanyaLayout.setOnClickListener {
            campaignAction(it)
        }
        petTakip.setOnClickListener{

        }
    }

    private fun campaignAction(view: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToCampaignFragment()
        Navigation.findNavController(view).navigate(action)

    }
    private fun petTracking(view: View) {

    }


}