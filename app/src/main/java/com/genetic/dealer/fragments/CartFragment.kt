package com.genetic.dealer.fragments

import android.os.Bundle
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
import kotlin.math.roundToInt

class CartFragment: Fragment(), CartProductListener {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartProductListener: CartProductListener = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerview.adapter = CartAdapter(cartProductListener, requireContext())
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.cart)
        binding.toolbarLayout.imgSearch.visibility = View.INVISIBLE
        binding.toolbarLayout.toolbarNavButton.setImageDrawable(ResourcesCompat.getDrawable(resources,
            R.drawable.ic_back_arrow, requireContext().theme))
        loadTotals()
    }

    override fun cartProductUpdates(key: String, value: CustomProductOptionModel?) {
        loadTotals()
    }

    private fun loadTotals() {
        val cart = (requireContext().applicationContext as DealerApplication).getProductCartList()
        var subtotal: Int = 0
        for (index in cart.entries) {
            subtotal += (index.value.productOption.optionAmount * index.value.quantity)
        }
        val tax: Int = (subtotal * 0.05).roundToInt()
        val discount: Int = 0
        val deliveryCharge: Int = 0
        val total = subtotal + tax - discount + deliveryCharge
        binding.subTotalValue.text = requireActivity().resources.getString(R.string.amount_s, subtotal.toString())
        binding.taxValue.text = requireActivity().resources.getString(R.string.amount_s, tax.toString())
        binding.discoveryValue.text = requireActivity().resources.getString(R.string.amount_s, discount.toString())
        binding.deliveryChargeValue.text = requireActivity().resources.getString(R.string.amount_s, deliveryCharge.toString())
        binding.totalValue.text = requireActivity().resources.getString(R.string.amount_s, total.toString())

    }
}