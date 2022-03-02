package com.example.loginsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.loginsample.auth.AuthActivity
import com.example.loginsample.auth.ui.HomeActivity
import com.example.loginsample.data.UserPreferences
import com.example.loginsample.utils.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // to check if token is saved or not
        val userPreferences = UserPreferences(this)

            userPreferences.authToken.asLiveData().observe(this, Observer {
                val activity =
                    if (it == null) AuthActivity::class.java else HomeActivity::class.java
                startNewActivity(activity)
            })



    }
}