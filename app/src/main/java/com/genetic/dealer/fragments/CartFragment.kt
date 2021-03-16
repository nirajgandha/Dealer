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


class CartFragment: Fragment(), CartProductListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartProductListener: CartProductListener = this
    private var preference: Preference? = null
    private var taxPercent: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preference = Preference(requireContext())
        getTaxPercentage()
        binding.cartRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerview.adapter = CartAdapter(cartProductListener, requireContext())
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.cart)
        binding.toolbarLayout.toolbarNavButton.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_back_arrow, requireContext().theme
            )
        )
        binding.submitCl.setOnClickListener {
            callPlaceOrder()
        }
    }

    private fun getTaxPercentage() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getTaxPercentage(preference?.getString(AppConstant.DEALER_ID, ""))
            .enqueue(object : Callback<PercentageResponse> {
                override fun onResponse(
                    call: Call<PercentageResponse>,
                    response: Response<PercentageResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            taxPercent = body.data.value.toDouble()
                        } else {
                            showError(meta.message)
                        }
                    } else {
                        showError(response.message())
                    }
                    loadTotals()
                }

                override fun onFailure(call: Call<PercentageResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                    loadTotals()
                }

            })
    }

    private fun callPlaceOrder() {
        val jsonForCart = getJsonForCart()
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .placeOrder(jsonForCart)
            .enqueue(object : Callback<PlaceOrderResponse> {
                override fun onResponse(
                    call: Call<PlaceOrderResponse>,
                    response: Response<PlaceOrderResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            val bundle = Bundle()
                            bundle.putString("orderId", body.data.orderList[0].orderId)
                            bundle.putString("amount", body.data.orderAmount.orderTotalAmount.toString())
                            val thankYouFragment = ThankYouFragment()
                            thankYouFragment.arguments = bundle
                            (requireActivity() as MainActivity).openOtherFragment(thankYouFragment)
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<PlaceOrderResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })
    }

    private fun getJsonForCart(): String {
        val cart = (requireContext().applicationContext as DealerApplication).getProductCartList()
        val jsonArray = JSONArray()
        for (index in cart.entries) {
            val productJson = JSONObject()
            productJson.put("product_id", index.value.productOption.productId)
            productJson.put("option_id", index.value.productOption.id)
            productJson.put("qty", index.value.quantity.toString())
            jsonArray.put(productJson)
        }

        val finalJsonObject = JSONObject()
        finalJsonObject.put("dealer_id", preference?.getString(AppConstant.DEALER_ID, ""))
        finalJsonObject.put("order", jsonArray)
        return finalJsonObject.toString()
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }

    override fun cartProductUpdates(key: String, value: CustomProductOptionModel?) {
        loadTotals()
    }

    private fun loadTotals() {
        val cart = (requireContext().applicationContext as DealerApplication).getProductCartList()
        var subtotal = 0.0
        for (index in cart.entries) {
            subtotal += (index.value.productOption.optionAmount * index.value.quantity)
        }
        val tax = (subtotal * (taxPercent/100))
        val discount = 0.0
        val deliveryCharge = 0.0
        val total = subtotal + tax - discount + deliveryCharge
        binding.subTotalValue.text = requireActivity().resources.getString(
            R.string.amount_s,
            subtotal.toString()
        )
        binding.taxValue.text = requireActivity().resources.getString(
            R.string.amount_s,
            tax.toString()
        )
        binding.discoveryValue.text = requireActivity().resources.getString(
            R.string.discount_s,
            discount.toString()
        )
        binding.deliveryChargeValue.text = requireActivity().resources.getString(
            R.string.amount_s,
            deliveryCharge.toString()
        )
        binding.totalValue.text = requireActivity().resources.getString(
            R.string.amount_s,
            total.toString()
        )
        if (cart.isEmpty()) {
            binding.submitCl.visibility = View.GONE
        } else {
            binding.submitCl.visibility = View.VISIBLE
        }

    }
}