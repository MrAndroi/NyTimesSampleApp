package com.sami.core.utils

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sami.core.R

fun showMaterialAlertDialog(
    context: Context,
    message: String,
    positiveButtonClickListener: (DialogInterface) -> Unit = {},
    cancelable: Boolean = true,
) {
    MaterialAlertDialogBuilder(context)
        .setTitle(null)
        .setMessage(message)
        .setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
            positiveButtonClickListener(
                dialog
            )
        }
        .setCancelable(cancelable)
        .setNegativeButton(null, null)
        .show()
}
