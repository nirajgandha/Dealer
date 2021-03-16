package com.genetic.dealer.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.databinding.FragmentHomeBinding
import com.genetic.dealer.model.DashboardData
import com.genetic.dealer.model.DashboardResponse
import com.genetic.dealer.model.OrderDetailResponse
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        preference = Preference(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDashboardDetails()
        loadData()
    }

    private fun getDashboardDetails() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getDashboard(preference?.getString(AppConstant.DEALER_ID, ""))
            .enqueue(object : Callback<DashboardResponse> {
                override fun onResponse(call: Call<DashboardResponse>,
                                        response: Response<DashboardResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            loadDataFromResponse(body.data)
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })
    }

    private fun loadDataFromResponse(dashboardData: DashboardData) {
        val recentOrder = dashboardData.recentOrder
        binding.recentOrderNumber.text = resources.getString(R.string.order_number, recentOrder.orderList[0].orderId)
        binding.salesManName.text = resources.getString(R.string.sales_man_s, recentOrder.orderList[0].salemanName)
        binding.type.text = resources.getString(R.string.str_type, recentOrder.orderList[0].productOptionTitle)
        binding.amount.text = resources.getString(R.string.amount_s, recentOrder.orderAmount.orderTotalAmount.toString())
        binding.txtViewDetail.text = recentOrder.orderList[0].orderStatus
        binding.detail.setOnClickListener {
            val popup = PopupMenu(requireContext(), binding.detail)
            popup.inflate(R.menu.order_navigation)
            popup.setOnMenuItemClickListener {
                if (it.itemId == R.id.view_detail) {
                    popup.dismiss()
                    val bundle = Bundle()
                    bundle.putString("orderId", recentOrder.orderList[0].id.toString())
                    val orderDetailFragment = OrderDetailFragment()
                    orderDetailFragment.arguments = bundle
                    (requireActivity() as MainActivity).openOtherFragment(orderDetailFragment)
                }
                true
            }
            popup.show()

        }
        binding.txtMorePlusRecentOrder.setOnClickListener {
            (requireActivity() as MainActivity).onItemClick(resources.getString(R.string.menu_order))
        }

        if (dashboardData.paymentHistory.isEmpty()) {
            binding.paymentHistoryLayout.visibility = View.GONE
        } else {
            binding.paymentHistoryLayout.visibility = View.VISIBLE
            if (dashboardData.paymentHistory.size < 2) {
                val payment = dashboardData.paymentHistory[0]
                binding.paymentHistoryDashboardLayout1.orderNo.text = payment.orderDetail[0].orderId
                binding.paymentHistoryDashboardLayout1.paymentType.text = payment.paymentDetail[0].type
                binding.paymentHistoryDashboardLayout1.amount.text =
                    resources.getString(R.string.amount_s,payment.paymentDetail[0].totalAmount.toString())
                val split = payment.paymentDetail[0].createdAt.split(" ")
                val date = split[0].split("-")
                binding.paymentHistoryDashboardLayout1.paymentDate.text = "${date[2]}-${date[1]}-${date[0]}"
                binding.paymentHistoryDashboardLayout1.paymentTime.text = split[1]
                binding.paymentHistoryDashboardLayout1.imgDetails.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("paymentId", payment.paymentDetail[0].id.toString())
                    val paymentDetailFragment = PaymentDetailFragment()
                    paymentDetailFragment.arguments = bundle
                    (requireActivity() as MainActivity).openOtherFragment(paymentDetailFragment)
                }
                binding.paymentHistoryDashboardLayout2.root.visibility = View.GONE
            } else {
                val payment = dashboardData.paymentHistory[0]
                binding.paymentHistoryDashboardLayout1.orderNo.text = payment.orderDetail[0].orderId
                binding.paymentHistoryDashboardLayout1.paymentType.text = payment.paymentDetail[0].type
                binding.paymentHistoryDashboardLayout1.amount.text =
                    resources.getString(R.string.amount_s,payment.paymentDetail[0].totalAmount.toString())
                val split = payment.paymentDetail[0].createdAt.split(" ")
                val date = split[0].split("-")
                binding.paymentHistoryDashboardLayout1.paymentDate.text = "${date[2]}-${date[1]}-${date[0]}"
                binding.paymentHistoryDashboardLayout1.paymentTime.text = split[1]
                binding.paymentHistoryDashboardLayout1.imgDetails.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("paymentId", payment.paymentDetail[0].id.toString())
                    val paymentDetailFragment = PaymentDetailFragment()
                    paymentDetailFragment.arguments = bundle
                    (requireActivity() as MainActivity).openOtherFragment(paymentDetailFragment)
                }
                binding.paymentHistoryDashboardLayout2.root.visibility = View.VISIBLE
                val payment1 = dashboardData.paymentHistory[1]
                binding.paymentHistoryDashboardLayout2.orderNo.text = payment1.orderDetail[0].orderId
                binding.paymentHistoryDashboardLayout2.paymentType.text = payment1.paymentDetail[0].type
                binding.paymentHistoryDashboardLayout2.amount.text =
                    resources.getString(R.string.amount_s,payment1.paymentDetail[0].totalAmount.toString())
                val split1 = payment1.paymentDetail[0].createdAt.split(" ")
                val date1 = split1[0].split("-")
                binding.paymentHistoryDashboardLayout2.paymentDate.text = "${date1[2]}-${date1[1]}-${date1[0]}"
                binding.paymentHistoryDashboardLayout2.paymentTime.text = split1[1]
                binding.paymentHistoryDashboardLayout2.imgDetails.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("paymentId", payment1.paymentDetail[0].id.toString())
                    val paymentDetailFragment = PaymentDetailFragment()
                    paymentDetailFragment.arguments = bundle
                    (requireActivity() as MainActivity).openOtherFragment(paymentDetailFragment)
                }
            }
        }
        binding.txtMorePlusPaymentDueHistory.setOnClickListener {
            (requireActivity() as MainActivity).onItemClick(resources.getString(R.string.menu_payment))
        }

        if (dashboardData.paymentDue.isEmpty()) {
            binding.paymentDueLayout.visibility = View.GONE
        } else {
            binding.paymentDueLayout.visibility = View.VISIBLE
            if (dashboardData.paymentDue.size < 2) {
                val payment = dashboardData.paymentDue[0]
                binding.paymentDueDashboardLayout1.orderNo.text = payment.orderDetail[0].orderId
                binding.paymentDueDashboardLayout1.paymentType.text = payment.paymentDetail[0].type
                binding.paymentDueDashboardLayout1.amount.text =
                    resources.getString(R.string.amount_s,payment.paymentDetail[0].totalAmount.toString())
                val split = payment.paymentDetail[0].createdAt.split(" ")
                val date = split[0].split("-")
                binding.paymentDueDashboardLayout1.paymentDate.text = "${date[2]}-${date[1]}-${date[0]}"
                binding.paymentDueDashboardLayout1.imgDetails.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("paymentId", payment.paymentDetail[0].id.toString())
                    val paymentDetailFragment = PaymentDetailFragment()
                    paymentDetailFragment.arguments = bundle
                    (requireActivity() as MainActivity).openOtherFragment(paymentDetailFragment)
                }
                binding.paymentDueDashboardLayout2.root.visibility = View.GONE
            } else {
                val payment = dashboardData.paymentDue[0]
                binding.paymentDueDashboardLayout1.orderNo.text = payment.orderDetail[0].orderId
                binding.paymentDueDashboardLayout1.paymentType.text = payment.paymentDetail[0].type
                binding.paymentDueDashboardLayout1.amount.text =
                    resources.getString(R.string.amount_s,payment.paymentDetail[0].totalAmount.toString())
                val split = payment.paymentDetail[0].createdAt.split(" ")
                val date = split[0].split("-")
                binding.paymentDueDashboardLayout1.paymentDate.text = "${date[2]}-${date[1]}-${date[0]}"
                binding.paymentDueDashboardLayout1.imgDetails.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("paymentId", payment.paymentDetail[0].id.toString())
                    val paymentDetailFragment = PaymentDetailFragment()
                    paymentDetailFragment.arguments = bundle
                    (requireActivity() as MainActivity).openOtherFragment(paymentDetailFragment)
                }
                binding.paymentDueDashboardLayout2.root.visibility = View.VISIBLE
                val payment1 = dashboardData.paymentDue[1]
                binding.paymentDueDashboardLayout2.orderNo.text = payment1.orderDetail[0].orderId
                binding.paymentDueDashboardLayout2.paymentType.text = payment1.paymentDetail[0].type
                binding.paymentDueDashboardLayout2.amount.text =
                    resources.getString(R.string.amount_s,payment1.paymentDetail[0].totalAmount.toString())
                val split1 = payment1.paymentDetail[0].createdAt.split(" ")
                val date1 = split1[0].split("-")
                binding.paymentDueDashboardLayout2.paymentDate.text = "${date1[2]}-${date1[1]}-${date1[0]}"
                binding.paymentDueDashboardLayout2.imgDetails.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("paymentId", payment1.paymentDetail[0].id.toString())
                    val paymentDetailFragment = PaymentDetailFragment()
                    paymentDetailFragment.arguments = bundle
                    (requireActivity() as MainActivity).openOtherFragment(paymentDetailFragment)
                }
            }
        }
        binding.txtMorePlusRecentOrder.setOnClickListener {
            (requireActivity() as MainActivity).onItemClick(resources.getString(R.string.menu_payment))
        }
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).openDrawer() }
        binding.toolbarLayout.screenTitle.text = resources.getString(R.string.menu_home)
    }
}