package com.example.tryingfreerasp

import android.app.Application
import android.util.Log
import com.aheaditec.talsec_security.security.api.Talsec
import com.aheaditec.talsec_security.security.api.TalsecConfig
import com.aheaditec.talsec_security.security.api.ThreatListener

class TalsecApplication : Application(), ThreatListener.ThreatDetected {
    override fun onCreate() {
        super.onCreate()

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
        private val expectedSigningCertificateHashBase64 = arrayOf("") // None
        private const val watcherMail = "petrisko.jaroslav.ml@gmail.com" // I guess it wont hurt to leave this here
        private val supportedAlternativeStores = emptyArray<String>()
        private val isProd = true
    }


    override fun onUntrustedInstallationSourceDetected() {
        Log.w("warning", "untrusted source detected")
        SafeApplication.safeInstallation.value = false
        SafeApplication.warningString = R.string.unauthorized_source
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

