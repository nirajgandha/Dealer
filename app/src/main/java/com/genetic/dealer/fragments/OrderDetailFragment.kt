package com.genetic.dealer.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.databinding.FragmentOrderDetailBinding
import com.genetic.dealer.databinding.ProductDetailListItemBinding
import com.genetic.dealer.model.OrderDetailData
import com.genetic.dealer.model.OrderDetailResponse
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailFragment : Fragment() {

    private var _binding : FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private var orderId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentOrderDetailBinding.inflate(inflater)
        preference = Preference(requireContext())
        orderId = requireArguments().getString("orderId", "")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding.toolbarLayout.toolbarNavButton.setImageDrawable(
            ResourcesCompat.getDrawable(resources,
            R.drawable.ic_back_arrow, requireContext().theme))
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        callGetOrderDetail()
    }

    private fun callGetOrderDetail() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getOrderDetail(preference?.getString(AppConstant.DEALER_ID, ""), orderId)
            .enqueue(object : Callback<OrderDetailResponse> {
                override fun onResponse(call: Call<OrderDetailResponse>,
                                        response: Response<OrderDetailResponse>) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            loadDataFromResponse(body.orderDetailData)
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<OrderDetailResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })

    }

    private fun loadDataFromResponse(orderDetailData: OrderDetailData) {
        binding.toolbarLayout.screenTitle.text = requireContext().resources.getString(R.string.order_number, orderDetailData.orderList[0].orderId)
        binding.salesManName.text = orderDetailData.orderList[0].salemanName
        binding.email.text = orderDetailData.orderList[0].salesmanEmail
        binding.contact.text = orderDetailData.orderList[0].salesmanMobileno.toString()
        for (item in orderDetailData.orderList) {
            val prodBinding: ProductDetailListItemBinding = ProductDetailListItemBinding.inflate(layoutInflater)
            prodBinding.productName.text = item.productName
            prodBinding.optionName.text = item.productOptionTitle
            prodBinding.qty.text = item.qty.toString()
            prodBinding.amount.text = item.amount.toString()
            binding.productsListView.addView(prodBinding.root)
        }

        binding.subTotalValue.text = requireContext().resources.getString(R.string.amount_s, orderDetailData.orderAmount.subTotal.toString())
        binding.taxValue.text = requireContext().resources.getString(R.string.amount_s, orderDetailData.orderAmount.tax.toString())
        binding.discoveryValue.text = requireContext().resources.getString(R.string.discount_s, orderDetailData.orderAmount.discount.toString())
        binding.deliveryChargeValue.text = requireContext().resources.getString(R.string.amount_s, orderDetailData.orderAmount.deliveryCharges.toString())
        binding.totalValue.text = requireContext().resources.getString(R.string.amount_s, orderDetailData.orderAmount.orderTotalAmount.toString())
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }
}