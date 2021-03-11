package com.genetic.dealer.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.genetic.dealer.customui.CustomDialog
import com.genetic.dealer.retrofit_api.APIClient
import com.genetic.dealer.retrofit_api.APIInterface


object Utils {
    /**
     * Call snack bar which will disapper in 3-5 seconds
     *
     * @return Snack bar
     */
    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    private var customDialog: CustomDialog? = null

    fun showProgress(context: Context) {
        customDialog = CustomDialog.getInstance()
        customDialog!!.showProgress(context, false)
    }

    fun hideProgress() {
        if (customDialog != null) {
            customDialog!!.hideProgress()
            customDialog = null
        }
    }

    fun getApiInterfaces(): APIInterface {
        return APIClient.getApiInterface()
    }
}