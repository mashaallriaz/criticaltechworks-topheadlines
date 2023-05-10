package com.criticaltechworks.topheadlines.ui.biometric

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BiometricAvailabilityChecker @Inject constructor(@ApplicationContext private val context: Context) {

    fun hasBiometricAuthenticator(): Boolean {
        var biometry: Int =
            if (Build.VERSION.SDK_INT >= 30) BiometricManager.from(context).canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK
            ) else BiometricManager.from(context).canAuthenticate()

        when (biometry) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED, BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> return false
            BiometricManager.BIOMETRIC_SUCCESS -> return true
        }
        return false
    }
}