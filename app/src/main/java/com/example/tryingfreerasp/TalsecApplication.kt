package com.example.tryingfreerasp

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aheaditec.talsec_security.security.api.Talsec
import com.aheaditec.talsec_security.security.api.TalsecConfig
import com.aheaditec.talsec_security.security.api.ThreatListener

class TalsecApplication : Application(), ThreatListener.ThreatDetected {
    override fun onCreate() {
        super.onCreate()
        print("lololka")

        // Uncomment the following Log.e(...) to get your expectedSigningCertificateHashBase64
        // Copy the result from logcat and assign to expectedSigningCertificateHashBase64
        // Log.e("SigningCertificateHash", Utils.computeSigningCertificateHash(this))

        val config = TalsecConfig(
            expectedPackageName,
            expectedSigningCertificateHashBase64,
            watcherMail,
            supportedAlternativeStores,
            isProd
        )

        ThreatListener(this).registerListener(this)
        Talsec.start(this, config)
    }

    companion object {
        private const val expectedPackageName = "com.example.tryingfreerasp"
        private val expectedSigningCertificateHashBase64 = arrayOf("dxfcgvhjbukilo") // None
        private const val watcherMail = "petrisko.jaroslav.ml@gmail.com" // I guess it wont hurt to leave this here
        private val supportedAlternativeStores = emptyArray<String>()
        private val isProd = true
    }
    override fun onUntrustedInstallationSourceDetected() {
        Log.w("warning", "untrusted source detected")
    }

    //Not supposed to do this but the class expects them to be implemented
    override fun onRootDetected() {}
    override fun onDebuggerDetected() {}
    override fun onEmulatorDetected() {}
    override fun onTamperDetected() {}
    override fun onHookDetected() {}
    override fun onDeviceBindingDetected() {}
    override fun onObfuscationIssuesDetected() {}
}

