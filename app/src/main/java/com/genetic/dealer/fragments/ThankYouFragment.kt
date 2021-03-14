package com.genetic.dealer.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.genetic.dealer.DealerApplication
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.adapter.CartAdapter
import com.genetic.dealer.databinding.FragmentCartBinding
import com.genetic.dealer.databinding.FragmentThankyouBinding
import com.genetic.dealer.interfaces.CartProductListener
import com.genetic.dealer.model.CustomProductOptionModel
import com.genetic.dealer.model.PaymentListResponse
import com.genetic.dealer.model.PercentageResponse
import com.genetic.dealer.model.PlaceOrderResponse
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class ThankYouFragment: Fragment(){
    private var _binding: FragmentThankyouBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThankyouBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        binding.homeTv.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.order_number, requireArguments().getString("orderId"))
        binding.totalOrderAmount.text = resources.getString(R.string.amount_s, requireArguments().getString("amount"))
        binding.toolbarLayout.toolbarNavButton.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_back_arrow, requireContext().theme
            )
        )

    }
}