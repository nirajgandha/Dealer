package com.genetic.dealer.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.genetic.dealer.R
import com.genetic.dealer.activity.MainActivity
import com.genetic.dealer.databinding.FragmentEditProfileBinding
import com.genetic.dealer.model.*
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.GlideApp
import com.genetic.dealer.utils.Preference
import com.genetic.dealer.utils.Utils
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.util.*


class ProfileFragment: Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private val REQUEST_CODE_FOR_ON_ACTIVITY_RESULT: Int = 1235

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preference = Preference(requireContext())
        getProfileDetail()
        binding.toolbarLayout.toolbarNavButton.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        binding.toolbarLayout.screenTitle.text = "Edit Profile"
        binding.toolbarLayout.toolbarNavButton.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_back_arrow, requireContext().theme
            )
        )
        binding.submit.setOnClickListener {
            if (binding.name.text.trim().isEmpty()) {
                showError("Enter Responsible Person's Name")
                return@setOnClickListener
            }
            if (binding.whatsapp.text.trim().isEmpty()) {
                showError("Enter Whatsapp Number")
                return@setOnClickListener
            }
            if (binding.mobile.text.trim().isEmpty()) {
                showError("Enter Mobile Number")
                return@setOnClickListener
            }
            if (binding.dob.text.trim().isEmpty()) {
                showError("Select Date of Birth")
                return@setOnClickListener
            }
            callUpdateProfile()
        }
    }

    private fun getProfileDetail() {
        Utils.showProgress(requireContext())
        APIClient.getApiInterface()
            .getProfile(preference?.getString(AppConstant.PHONE, ""))
            .enqueue(object : Callback<GetProfileResponse> {
                override fun onResponse(
                    call: Call<GetProfileResponse>,
                    response: Response<GetProfileResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        val meta = body.meta
                        if (meta.code.equals("200")) {
                            setProfile(body.data[0])
                        } else {
                            showError(meta.message)
                        }

                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })
    }

    private fun setProfile(profileItem: ProfileItem) {
        GlideApp.with(requireContext())
            .load(profileItem.image)
            .into(binding.profilePic)
            .onLoadFailed(ContextCompat.getDrawable(requireContext(), R.drawable.logo))
        binding.name.setText(profileItem.responsiblePersonName)
        binding.whatsapp.setText(profileItem.whatsappno.toString())
        binding.mobile.setText(profileItem.mobileno.toString())
        binding.dob.text = profileItem.birthdate
        binding.calendar.setOnClickListener {
            val calendar = Calendar.getInstance()
            if (binding.dob.text.toString().isNotEmpty()) {
                val splitter = binding.dob.text.toString().split("-")
                calendar.set(Calendar.YEAR, splitter[0].toInt())
                calendar.set(Calendar.MONTH, splitter[1].toInt() - 1)
                calendar.set(Calendar.DAY_OF_MONTH, splitter[2].toInt())
            }
            val mYear = calendar.get(Calendar.YEAR)
            val mMonth = calendar.get(Calendar.MONTH)
            val mDay = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                binding.dob.text = "$year-${month + 1}-$dayOfMonth"
            }, mYear, mMonth, mDay).show()
        }
        binding.dob.setOnClickListener { binding.calendar.performClick() }
        binding.profilePic.setOnClickListener {
            requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.deleteRecursively()
            val filesIntent = Intent(Intent.ACTION_GET_CONTENT)
            filesIntent.type = "image/*"
            startActivityForResult(filesIntent, REQUEST_CODE_FOR_ON_ACTIVITY_RESULT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOR_ON_ACTIVITY_RESULT){
                // Checking whether data is null or not
                data?.data?.let { returnUri ->
                    val cursor = requireContext().contentResolver.query(
                        returnUri,
                        null,
                        null,
                        null,
                        null
                    )!!
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    val uploadFile = File(
                        requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                        cursor.getString(
                            nameIndex
                        )
                    )
                    cursor.close()
                    var inputStream = requireContext().contentResolver.openInputStream(returnUri)
                    val outputSteam = FileOutputStream(uploadFile)
                    val buffer = ByteArray(1024)
                    var read: Int
                    while (inputStream!!.read(buffer).also { read = it } != -1) {
                        outputSteam.write(buffer, 0, read)
                    }
                    inputStream.close()

                    // write the output file (You have now copied the file)

                    // write the output file (You have now copied the file)
                    outputSteam.flush()
                    outputSteam.close()
                    val files = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.listFiles()
                    if (!files.isNullOrEmpty()) {
                        binding.profilePic.setImageBitmap(BitmapFactory.decodeFile(files[0].absolutePath))
                    }
                }
            }
        }
    }

    private fun callUpdateProfile() {
        Utils.showProgress(requireContext())
        val mediaType = "text/plain".toMediaType()
        val dealer_id = preference!!.getString(AppConstant.DEALER_ID, "").toRequestBody(mediaType)
        val responsible_person_name = binding.name.text.toString().toRequestBody(mediaType)
        val whatsappno = binding.whatsapp.text.toString().toRequestBody(mediaType)
        val mobile = binding.mobile.text.toString().toRequestBody(mediaType)
        val dob = binding.dob.text.toString().toRequestBody(mediaType)
        val fileList = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.listFiles()
        val files = if (fileList.isNullOrEmpty()) {
            MultipartBody.Part.createFormData("image", "", "".toRequestBody(mediaType))
        } else {
            val file = fileList[0]
            val fileBody: RequestBody = file.asRequestBody(
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    file.extension
                )!!.toMediaType()
            )
            MultipartBody.Part.createFormData("image", file.name, fileBody)
        }
        APIClient.getApiInterface()
            .updateProfile(dealer_id, responsible_person_name, whatsappno, mobile, dob, files)
            .enqueue(object : Callback<UpdateProfileResponse> {
                override fun onResponse(
                    call: Call<UpdateProfileResponse>,
                    response: Response<UpdateProfileResponse>
                ) {
                    Utils.hideProgress()
                    val body = response.body()
                    if (body != null) {
                        showError(body.meta.message)
                    } else {
                        showError(response.message())
                    }

                }

                override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                    Utils.hideProgress()
                    showError("Error occurred!! Please try again later")
                    t.printStackTrace()
                }

            })
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string)
    }
}