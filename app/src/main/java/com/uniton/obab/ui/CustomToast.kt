package com.uniton.obab.ui

import android.app.Activity
import android.content.res.Resources
import android.widget.TextView
import android.widget.Toast
import com.uniton.obab.R

fun Toast.showCustomToast(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.custom_toast,
        activity.findViewById(R.id.toast_tv)
    )

    val textView = layout.findViewById<TextView>(R.id.toast_tv)
    textView.text = message

    this.apply {
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }

}

private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()