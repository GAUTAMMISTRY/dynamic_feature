package com.kirill.dynamicfeature

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.play.core.splitcompat.SplitCompat

class FeaturesApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
        Log.i("App", "Attach context happened")
    }

}