package com.codecanyon.umes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codecanyon.umes.R
import com.codecanyon.umes.util.Singleton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (!Singleton.hideNavView())
            Singleton.showExitAppDialog(this)
    }
}