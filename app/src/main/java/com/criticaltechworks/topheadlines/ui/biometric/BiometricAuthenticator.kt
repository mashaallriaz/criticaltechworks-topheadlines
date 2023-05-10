package com.criticaltechworks.topheadlines.ui.biometric

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BiometricAuthenticator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val biometricAvailabilityChecker: BiometricAvailabilityChecker
) {

    fun authenticate(activity: AppCompatActivity) {
        if (biometricAvailabilityChecker.hasBiometricAuthenticator()) {
            showBiometricPrompt(activity)
        }
    }

    private fun showBiometricPrompt(activity: AppCompatActivity) {
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {})

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Fingerprint Scanner")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}