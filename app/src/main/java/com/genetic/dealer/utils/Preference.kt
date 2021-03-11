package com.genetic.dealer.utils

import android.content.Context
import android.content.SharedPreferences
import com.genetic.dealer.R

/**
 * Preference class to use SharedPreference class through out application. Use this class to store or retrieve data from SharedPreference.
 */
class Preference(context: Context) {
    /**
     * Preference key for userId
     */
    val ID = "ID"

    /**
     * Shared Preference instance
     */
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE) as SharedPreferences

    fun getString(key: String, default: String): String {
        return sharedPreferences.getString(key, default)!!
    }

    fun setString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    fun setInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    /**
     * Clears the all Shared Preference data
     */
    fun clearAllPreferenceData() {
        val editor = sharedPreferences.edit()
        editor.clear().commit()
    }

}