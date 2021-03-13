package com.genetic.dealer.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.genetic.dealer.DealerApplication
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.adapter.ProductAdapter
import com.genetic.dealer.adapter.ProductOptionAdapter
import com.genetic.dealer.databinding.FragmentProductBinding
import com.genetic.dealer.databinding.ProductOptionDialogBinding
import com.genetic.dealer.interfaces.ProductItemClickListener
import com.genetic.dealer.interfaces.ProductOptionListener
import com.genetic.dealer.model.*
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductFragment : Fragment(), ProductItemClickListener, ProductOptionListener {

    private var _binding : FragmentProductBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private var mProductCategoryDetail: ArrayList<String>? = null
    private var mProductItemClickListener: ProductItemClickListener? = null
    private var mProductOptionListener: ProductOptionListener? = null
    private var productOption: ProductOption? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentProductBinding.inflate(inflater)
        preference = Preference(requireContext())
        mProductCategoryDetail = requireArguments().getStringArrayList(AppConstant.PRODUCT_CATEGORY)
        mProductItemClickListener = this
        mProductOptionListener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).openDrawer() }
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.product_screen)
        binding.productRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        updateCartStatus()
        getProductListFromCategory()
    }

    private fun getProductListFromCategory() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getProductList(preference?.getString(AppConstant.DEALER_ID, ""),
                mProductCategoryDetail!![0]
            )
            .enqueue(object : Callback<ProductListResponse> {
                override fun onResponse(
                    call: Call<ProductListResponse>,
                    response: Response<ProductListResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            binding.productRecyclerview.adapter = ProductAdapter(body.data, mProductItemClickListener!!, requireContext())
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })
    }

    override fun onProductItemClick(productListItem: ProductListItem) {
        Log.d("niraj",productListItem.toString())
        getProductsOptionList(productListItem.id.toString())
    }

    private fun getProductsOptionList(productId: String) {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getProductOptionList(preference?.getString(AppConstant.DEALER_ID, ""), productId)
            .enqueue(object : Callback<ProductOptionListResponse> {
                override fun onResponse(call: Call<ProductOptionListResponse>,
                                        response: Response<ProductOptionListResponse>)
                {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            showProductOptionDialog(body.data)
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<ProductOptionListResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })
    }

    private fun showProductOptionDialog(productOptionList: ArrayList<ProductOption>) {
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = ProductOptionDialogBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        dialogBinding.rbtRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        dialogBinding.rbtRecycler.adapter = ProductOptionAdapter(productOptionList, mProductOptionListener!!, requireContext())
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialogBinding.btnCancel.setOnClickListener { dialog.dismiss() }
        dialogBinding.btnOk.setOnClickListener {
            if (productOption != null) {
                val productOptionKey: String = "${productOption?.productId}-${productOption?.productName}-${productOption?.optionTitle}-${productOption?.id}"
                val customProductModel = CustomProductOptionModel(productOption, 1)
                ((requireActivity() as MainActivity).applicationContext as DealerApplication).getProductCartList().putIfAbsent(productOptionKey, customProductModel)
                if(!((requireActivity() as MainActivity).applicationContext as DealerApplication).getKeyArrayList().contains(productOptionKey)) {
                    ((requireActivity() as MainActivity).applicationContext as DealerApplication).getKeyArrayList().add(productOptionKey)
                }
            }
            updateCartStatus()
            printItemsInCart()
            dialog.dismiss()
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun updateCartStatus() {
        if (((requireActivity() as MainActivity).applicationContext as DealerApplication).getProductCartList().isNotEmpty()) {
            binding.toolbarLayout.imgCart.visibility = View.VISIBLE
            binding.toolbarLayout.cartCount.visibility = View.VISIBLE
            binding.toolbarLayout.cartCount.text = ((requireActivity() as MainActivity).applicationContext as DealerApplication).getProductCartList().size.toString()
            binding.toolbarLayout.imgCart.setOnClickListener {
                (requireActivity() as MainActivity).openOtherFragment(CartFragment())
            }
        } else {
            binding.toolbarLayout.imgCart.visibility = View.GONE
            binding.toolbarLayout.cartCount.visibility = View.GONE
            binding.toolbarLayout.cartCount.text = ""
        }
    }

    private fun printItemsInCart() {
        val cart = ((requireActivity() as MainActivity).applicationContext as DealerApplication).getProductCartList()
        for (index in cart.entries) {
            Log.d("Niraj","key: ${index.key}\n value: ${index.value.productOption}\n quantity: ${index.value.quantity}")
        }
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }

    override fun onProductOptionClick(productOption: ProductOption) {
        this.productOption = productOption
    }
}