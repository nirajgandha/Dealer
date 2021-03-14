package com.genetic.dealer.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.genetic.dealer.R
import com.genetic.dealer.databinding.ActivityLoginBinding
import com.genetic.dealer.model.LoginResponse
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private var token: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDeviceTokenFirebase()
        binding.btnSubmit.setOnClickListener {
            val validationError = validateMobileNumber()
            if (validationError == getString(R.string.empty_string)){
                callLoginApi()
            } else {
                showError(validationError)
            }
        }
    }

    private fun callLoginApi() {
        Utils.showProgress(this)
        APIClient.getApiInterface()
                .loginApi(binding.edtMobile.text.toString(), getString(R.string.device_type), token!!)
                .enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null){
                    val meta = body.meta
                    if (meta.code.equals("200", true)){
                        val data = body.loginData
                        val bundle = Bundle()
                        bundle.putString(getString(R.string.phone), data.phone)
                        bundle.putString(getString(R.string.otp), data.otp.toString())
                        val intent = Intent(this@LoginActivity, OtpActivity::class.java)
                        intent.putExtra(getString(R.string.otpBundle), bundle)
                        startActivity(intent)
                        finish()
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun validateMobileNumber(): String {
        return when {
            binding.edtMobile.text.isEmpty() -> {
                getString(R.string.mobile_empty_error)
            }
            !binding.edtMobile.text.isDigitsOnly() -> {
                getString(R.string.enter_mobile_only)
            }
            binding.edtMobile.text.length != 10 -> {
                getString(R.string.enter_mobile_number_complete)
            }
            else -> {
                getString(R.string.empty_string)
            }
        }
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }

    private fun getDeviceTokenFirebase(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(LoginActivity::class.toString(), "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            token = task.result.toString()
            Preference(this).setString(AppConstant.TOKEN, token.toString())
        })
    }
}