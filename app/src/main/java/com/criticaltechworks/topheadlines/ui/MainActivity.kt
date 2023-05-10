package com.criticaltechworks.topheadlines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.criticaltechworks.topheadlines.R
import com.criticaltechworks.topheadlines.ui.biometric.BiometricAuthenticator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var biometricAuthenticator: BiometricAuthenticator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        biometricAuthenticator.authenticate(this)
    }
}