package com.example.todolistkt

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setTime")
fun setTime(view: TextView, time: Long) {
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
    val text = format.format(Date(time))
    view.text = text
}