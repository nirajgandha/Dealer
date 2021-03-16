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
import com.genetic.dealer.databinding.FragmentChangePasswordBinding
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

class ChangePasswordFragment : Fragment() {

    private var _binding : FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater)
        preference = Preference(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
    }

    /*private fun getProductListFromCategory() {
         Utils.showProgress(requireContext())
         APIClient.getApiInterface()
             .getProductList(preference?.getString(AppConstant.DEALER_ID, ""),

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
     }*/

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }
}