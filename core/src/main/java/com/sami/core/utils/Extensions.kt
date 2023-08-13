package com.sami.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sami.core.R

fun Context.openUrl(url: String?) {
    if (url.isNullOrEmpty()) return
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    } catch (e: Exception) {
        return
    }
}

@SuppressLint("MissingPermission")
fun Context.hasNetwork(): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun Fragment.showPrimaryMessage(message: String, duration: Int = 1000) {
    Snackbar.make(requireView(), message, duration)
        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.black))
        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        .show()
}