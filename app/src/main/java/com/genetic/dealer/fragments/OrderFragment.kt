package com.genetic.dealer.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.adapter.OrderAdapter
import com.genetic.dealer.adapter.ProductAdapter
import com.genetic.dealer.databinding.FragmentHomeBinding
import com.genetic.dealer.databinding.FragmentOrderBinding
import com.genetic.dealer.interfaces.OrderItemClickListener
import com.genetic.dealer.model.OrderItem
import com.genetic.dealer.model.OrderListResponse
import com.genetic.dealer.model.ProductListResponse
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderFragment : Fragment(), OrderItemClickListener {

    private var _binding : FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private val orderItemClickListener: OrderItemClickListener = this

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
        binding.orderRecycler.layoutManager = LinearLayoutManager(requireContext())
        callGetOrderList()
    }

    private fun callGetOrderList() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getOrderList(preference?.getString(AppConstant.DEALER_ID, ""))
            .enqueue(object : Callback<OrderListResponse> {
                override fun onResponse(
                    call: Call<OrderListResponse>,
                    response: Response<OrderListResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            binding.orderRecycler.adapter = OrderAdapter(body.data, orderItemClickListener, requireContext())
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })

    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }

    override fun onOrderItemClick(orderItem: OrderItem) {
        val bundle = Bundle()
        bundle.putString("orderId", orderItem.id.toString())
        val orderDetailFragment = OrderDetailFragment()
        orderDetailFragment.arguments = bundle
        (requireActivity() as MainActivity).openOtherFragment(orderDetailFragment)
    }
}