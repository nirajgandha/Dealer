package com.genetic.dealer.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.databinding.FragmentHomeBinding
import com.genetic.dealer.databinding.FragmentOrderBinding
import com.genetic.dealer.utils.Preference

class OrderFragment : Fragment() {

    private var _binding : FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentOrderBinding.inflate(inflater)
        preference = Preference(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).openDrawer() }
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.menu_order)
    }
}