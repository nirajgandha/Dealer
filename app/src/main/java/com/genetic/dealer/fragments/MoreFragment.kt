package com.genetic.dealer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.databinding.FragmentMoreBinding
import com.genetic.dealer.utils.Preference

class MoreFragment : Fragment() {

    private var _binding : FragmentMoreBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMoreBinding.inflate(inflater)
        preference = Preference(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).openDrawer() }
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.menu_more)
        binding.myProfile.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(ProfileFragment()) }
        binding.changePassword.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(ChangePasswordFragment()) }
        binding.orders.setOnClickListener { (requireActivity() as MainActivity).onItemClick(resources.getString(R.string.menu_order)) }
        binding.payment.setOnClickListener { (requireActivity() as MainActivity).onItemClick(resources.getString(R.string.menu_payment)) }
        binding.paidHistory.setOnClickListener { (requireActivity() as MainActivity).onItemClick(resources.getString(R.string.menu_payment)) }
        binding.dueHistory.setOnClickListener { (requireActivity() as MainActivity).onItemClick(resources.getString(R.string.menu_payment)) }
        binding.logout.setOnClickListener {
            preference!!.clearAllPreferenceData()
            (requireActivity() as MainActivity).recreate()
        }
    }
}