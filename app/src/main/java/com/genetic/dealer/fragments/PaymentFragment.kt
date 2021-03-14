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
import com.genetic.dealer.adapter.PaymentAdapter
import com.genetic.dealer.databinding.FragmentPaymentBinding
import com.genetic.dealer.interfaces.PaymentItemClickListener
import com.genetic.dealer.model.PaymentItem
import com.genetic.dealer.model.PaymentListResponse
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentFragment : Fragment(), PaymentItemClickListener {

    private var _binding : FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private val paymentItemClickListener: PaymentItemClickListener = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPaymentBinding.inflate(inflater)
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
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.menu_payment)
        binding.paymentRecycler.layoutManager = LinearLayoutManager(requireContext())
        callGetPaymentList()
    }

    private fun callGetPaymentList() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getPaymentList(preference?.getString(AppConstant.DEALER_ID, ""))
            .enqueue(object : Callback<PaymentListResponse> {
                override fun onResponse(
                    call: Call<PaymentListResponse>,
                    response: Response<PaymentListResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            binding.paymentRecycler.adapter = PaymentAdapter(
                                body.data,
                                paymentItemClickListener,
                                requireContext()
                            )
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<PaymentListResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })

    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }

    override fun onPaymentItemClick(paymentItem: PaymentItem) {
        val bundle = Bundle()
        bundle.putString("paymentId", paymentItem.id.toString())
        val paymentDetailFragment = PaymentDetailFragment()
        paymentDetailFragment.arguments = bundle
        (requireActivity() as MainActivity).openOtherFragment(paymentDetailFragment)
    }
}