package com.genetic.dealer.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import com.genetic.dealer.R
import com.genetic.dealer.utils.AppConstant
import com.genetic.dealer.utils.Preference

class SplashActivity : AppCompatActivity() {
    val EXTERNAL_STORAGE_REQUEST = 12345
    var preference: Preference?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = Preference(this)
        setContentView(R.layout.activity_splash)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (!haveStoragePermission()) {
                askForPermission()
            } else {
                startMainTask()
            }
        } else {
            startMainTask()
        }
    }

    private fun askForPermission() {
        val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(this, permissions, EXTERNAL_STORAGE_REQUEST)
    }

    private fun startMainTask() {
        val id = preference!!.getString(AppConstant.DEALER_ID, "")
        Handler().postDelayed({
            val intent = if (id.isEmpty()) {
                preference!!.clearAllPreferenceData()
                Intent(this, LoginActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000)
    }

    private fun haveStoragePermission(): Boolean{
        val readPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val writePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (readPermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var allPerms = true
        if (requestCode == EXTERNAL_STORAGE_REQUEST) {
            for (perm in grantResults) {
                allPerms = allPerms && (perm == PackageManager.PERMISSION_GRANTED)
            }
            if (allPerms) {
                startMainTask()
            } else {
                askForPermission()
            }
        }
    }
}