package com.kirill.dynamicfeature

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private lateinit var splitInstallManager : SplitInstallManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        installRegistrationModule()

        uiStartReg.setOnClickListener {
            if (splitInstallManager.installedModules.contains("registration")) {
                val i = Intent()
                i.setClassName(BuildConfig.APPLICATION_ID, "com.kirill.dynamicfeature.registration.RegistrationActivity")
                i.putExtra("ExtraInt", 3) // Test intent for Dynamic feature
                startActivity(i)
            } else {
                Log.e(tag, "Registration feature is not installed")
            }
        }
    }

    private fun installRegistrationModule() {
        splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
        val request = SplitInstallRequest.newBuilder()
            .addModule("registration")
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                Log.d(tag, it.toString())}
            .addOnFailureListener {
                Log.e(tag, it.toString())}
    }
}
