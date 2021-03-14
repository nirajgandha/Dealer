package com.genetic.dealer.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.databinding.FragmentPaymentDetailBinding
import com.genetic.dealer.model.PaymentDetailData
import com.genetic.dealer.model.PaymentDetailResponse
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.GlideApp
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentDetailFragment : Fragment() {

    private var _binding : FragmentPaymentDetailBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private var paymentId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPaymentDetailBinding.inflate(inflater)
        preference = Preference(requireContext())
        paymentId = requireArguments().getString("paymentId", "")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding.toolbarLayout.screenTitle.text = "Payment Detail"
            binding.toolbarLayout.toolbarNavButton.setImageDrawable(
            ResourcesCompat.getDrawable(resources,
            R.drawable.ic_back_arrow, requireContext().theme))
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        callGetPaymentDetail()
    }

    private fun callGetPaymentDetail() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getPaymentDetail(preference?.getString(AppConstant.DEALER_ID, ""), paymentId)
            .enqueue(object : Callback<PaymentDetailResponse> {
                override fun onResponse(call: Call<PaymentDetailResponse>,
                                        response: Response<PaymentDetailResponse>) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            loadDataFromResponse(body.paymentDetailData)
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<PaymentDetailResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })

    }

    private fun loadDataFromResponse(paymentDetailData: PaymentDetailData) {
        binding.salesManName.text = paymentDetailData.paymentDetail.salemanName
        binding.email.text = paymentDetailData.paymentDetail.salesmanEmail
        binding.contact.text = paymentDetailData.paymentDetail.salesmanMobileno.toString()

        binding.orderNo.text = paymentDetailData.orderDetail[0].orderId
        binding.date.text  = paymentDetailData.orderDetail[0].orderDate.split(" ")[0]
        binding.amount.text  = requireContext().resources.getString(R.string.amount_s,paymentDetailData.orderDetail[0].totalAmount.toString())

        binding.paymentAmount.text = paymentDetailData.paymentDetail.totalAmount.toString()
        binding.paymentDate.text = paymentDetailData.paymentDetail.paymentDate.split(" ")[0]
        binding.type.text = paymentDetailData.paymentDetail.type

        Log.d("Niraj", "loadDataFromResponse: ${paymentDetailData.paymentDetail.image}")
        if (paymentDetailData.paymentDetail.image.isNotEmpty()) {
            GlideApp.with(requireContext())
                .load(paymentDetailData.paymentDetail.image)
                .fitCenter()
                .into(binding.roundImgLayout.roundedImageView)
                .onLoadFailed(
                    ResourcesCompat.getDrawable(
                        requireContext().resources,
                        R.drawable.logo,
                        requireContext().theme
                    )
                )
        }

    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }
}