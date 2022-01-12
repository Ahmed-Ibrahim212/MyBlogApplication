package com.olamachia.simpleblogapp.util

import android.view.View
import android.widget.ProgressBar

object Utils {

    fun showProgressBar(progressBar: ProgressBar, isDisplayed: Boolean) {
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}