package com.codecanyon.umes.util

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar

fun String.show(v: View, msg: String){
    Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show()
}

@BindingAdapter("android:setTextData")
fun setTextData(v: EditText, data: String?){
    data?.let {
        v.setText(it)
    }
}