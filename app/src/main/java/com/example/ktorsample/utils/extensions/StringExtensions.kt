package com.example.ktorsample.utils.extensions

import android.util.Log

fun String?.isValid() = this != null && this.isNotEmpty() && !this.equals("null",true)

fun Any?.print(){
    Log.d("LogTagKoin", " $this")
}