package com.sami.core.navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle

const val BUNDLE_DATA_KEY = "bundleDataKey"
fun Activity.navigateToScreen(
    screen: Screen,
    bundle: Bundle? = null,
    navigateAndRemoveFromStack: Boolean = false,
) {
    val intent = Intent()
    intent.setClassName(this, screen.screenName)
    intent.putExtra(BUNDLE_DATA_KEY, bundle)
    this.startActivity(intent)
    if (navigateAndRemoveFromStack) this.finish()
}