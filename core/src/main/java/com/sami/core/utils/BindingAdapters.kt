package com.sami.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sami.core.R

@BindingAdapter("imageUrl", "radius")
fun ImageView.imageUrl(url: String?, radius: Int) {
    this.load(url) {
        transformations(RoundedCornersTransformation(radius.toFloat()))
        error(R.drawable.error_placeholder)
        crossfade(true)
    }
}